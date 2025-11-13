// src/components/Category/CategoryForm/CategoryForm.jsx
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { toast } from "sonner";
import CategoryService from "../../../services/CategoryService";
import IconButton from "../../IconButton";
import { MdAddCircle } from "react-icons/md";
import { FaRegEdit, FaTrash, FaSave } from "react-icons/fa";

/**
 * Formulario para registrar, actualizar y eliminar categorías.
 */
function CategoryForm({ onCategoryChanged }) {
  const [type, setType] = useState("");
  const [prioritylevel, setPrioritylevel] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");
  const [updateId, setUpdateId] = useState("");
  const [isUpdating, setIsUpdating] = useState(false);

  const clearFields = () => {
    setType("");
    setPrioritylevel("");
    setCategory("");
    setDescription("");
    setUpdateId("");
    setIsUpdating(false);
  };

  const validateFields = () => {
    if (!type || !prioritylevel || !category || !description) {
      toast.error("Todos los campos son obligatorios.");
      return false;
    }
    return true;
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!validateFields()) return;
    try {
      await CategoryService.save({ type, prioritylevel, category, description });
      toast.success("Categoría registrada correctamente.");
      clearFields();
      onCategoryChanged();
    } catch {
      toast.error("Error al registrar la categoría.");
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    if (!updateId) return toast.error("Ingrese un ID para actualizar.");
    if (!validateFields()) return;
    try {
      await CategoryService.update(updateId, { type, prioritylevel, category, description });
      toast.success("Categoría actualizada correctamente.");
      clearFields();
      onCategoryChanged();
    } catch {
      toast.error("Error al actualizar la categoría.");
    }
  };

  const handleDelete = async () => {
    if (!updateId) return toast.error("Ingrese un ID para eliminar.");
    try {
      await CategoryService.delete(updateId);
      toast.success("Categoría eliminada correctamente.");
      clearFields();
      onCategoryChanged();
    } catch {
      toast.error("Error al eliminar la categoría.");
    }
  };

  return (
    <form onSubmit={isUpdating ? handleUpdate : handleSave}>
      <fieldset className="p-3 bg-light rounded border">
        <legend className="fw-bold">Gestión de Categorías</legend>
        <div className="row">
          <div className="col-md-3">
            <label>Tipo</label>
            <input className="form-control" value={type} onChange={(e) => setType(e.target.value)} />
          </div>
          <div className="col-md-3">
            <label>Nivel de prioridad</label>
            <select
              className="form-select"
              value={prioritylevel}
              onChange={(e) => setPrioritylevel(e.target.value)}
            >
              <option value="">[Seleccionar]</option>
              <option value="Alto">Alto</option>
              <option value="Medio">Medio</option>
              <option value="Bajo">Bajo</option>
            </select>
          </div>
          <div className="col-md-3">
            <label>Categoría</label>
            <select className="form-select" value={category} onChange={(e) => setCategory(e.target.value)}>
              <option value="">[Seleccionar]</option>
              <option value="Software">Software</option>
              <option value="Hardware">Hardware</option>
            </select>
          </div>
          <div className="col-md-3">
            <label>Descripción</label>
            <input
              className="form-control"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>
        </div>

        <div className="d-flex justify-content-center gap-3 mt-3">
          <IconButton type="submit" className="btn btn-danger" icon={MdAddCircle}>
            Registrar
          </IconButton>
          <IconButton
            type="button"
            className="btn btn-secondary"
            icon={FaRegEdit}
            onClick={() => setIsUpdating(true)}
          >
            Actualizar
          </IconButton>
          <IconButton type="button" className="btn btn-warning" icon={FaTrash} onClick={handleDelete}>
            Eliminar
          </IconButton>
          {isUpdating && (
            <IconButton type="submit" className="btn btn-success" icon={FaSave}>
              Guardar cambios
            </IconButton>
          )}
        </div>
      </fieldset>
    </form>
  );
}

export default CategoryForm;
