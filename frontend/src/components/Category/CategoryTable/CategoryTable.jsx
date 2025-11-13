// src/components/CategoryTable.jsx
import React, { useEffect, useState } from "react";
import CategoryService from "../services/CategoryService";

const CategoryTable = () => {
  const [categories, setCategories] = useState([]);
  const [formData, setFormData] = useState({ id: "", name: "" });
  const [isEditing, setIsEditing] = useState(false);

  // Cargar categorías al iniciar
  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await CategoryService.getAllCategories();
      setCategories(response.data);
    } catch (error) {
      console.error("Error al obtener categorías:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await CategoryService.updateCategory(formData.id, formData);
      } else {
        await CategoryService.createCategory(formData);
      }
      fetchCategories();
      setFormData({ id: "", name: "" });
      setIsEditing(false);
    } catch (error) {
      console.error("Error al guardar categoría:", error);
    }
  };

  const handleEdit = (category) => {
    setFormData(category);
    setIsEditing(true);
  };

  const handleDelete = async (id) => {
    try {
      await CategoryService.deleteCategory(id);
      fetchCategories();
    } catch (error) {
      console.error("Error al eliminar categoría:", error);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-3 text-center">Gestión de Categorías</h2>

      <form onSubmit={handleSubmit} className="mb-4">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Nombre de categoría"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            required
          />
          <button type="submit" className="btn btn-primary">
            {isEditing ? "Actualizar" : "Guardar"}
          </button>
          {isEditing && (
            <button
              type="button"
              className="btn btn-secondary"
              onClick={() => {
                setFormData({ id: "", name: "" });
                setIsEditing(false);
              }}
            >
              Cancelar
            </button>
          )}
        </div>
      </form>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {categories.map((cat) => (
            <tr key={cat.id}>
              <td>{cat.id}</td>
              <td>{cat.name}</td>
              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => handleEdit(cat)}
                >
                  Editar
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDelete(cat.id)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CategoryTable;
