// src/services/CategoryService.js
import axios from "axios";
import { getAuthHeader } from "../Utils/Auth";

const BASE_URL = "http://localhost:8080/api/ucv";

/**
 * Servicio encargado de gestionar las peticiones HTTP relacionadas con la entidad Category.
 */
const CategoryService = {
  getAll: async () => {
    return axios.get(`${BASE_URL}/categoryList`, {
      headers: getAuthHeader(),
    });
  },

  save: async (data) => {
    return axios.post(`${BASE_URL}/categorySave`, data, {
      headers: { "Content-Type": "application/json", ...getAuthHeader() },
    });
  },

  update: async (id, data) => {
    return axios.put(`${BASE_URL}/categoryUpdate/${id}`, data, {
      headers: { "Content-Type": "application/json", ...getAuthHeader() },
    });
  },

  delete: async (id) => {
    return axios.delete(`${BASE_URL}/categoryDelete/${id}`, {
      headers: getAuthHeader(),
    });
  },
};

export default CategoryService;
