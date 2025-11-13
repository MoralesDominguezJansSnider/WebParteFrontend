// src/components/Category/Category.jsx
import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import CategoryService from "../../services/CategoryService";
import CategoryForm from "./CategoryForm/CategoryForm";
import CategoryTable from "./CategoryTable/CategoryTable";
import { toast } from "sonner";

/**
 * Vista principal que integra el formulario y la tabla de categorías.
 */
const Category = () => {
  const [categoryList, setCategoryList] = useState([]);

  const fetchCategoryList = async () => {
    try {
      const res = await CategoryService.getAll();
      setCategoryList(res.data);
    } catch {
      toast.error("Error al cargar las categorías.");
    }
  };

  useEffect(() => {
    fetchCategoryList();
  }, []);

  const handleCategoryChanged = () => {
    fetchCategoryList();
  };

  return (
    <>
      <CategoryForm onCategoryChanged={handleCategoryChanged} />
      <CategoryTable categoryList={categoryList} />
    </>
  );
};

export default Category;
