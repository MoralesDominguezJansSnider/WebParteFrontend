import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

// 🔹 Páginas (módulos principales)
import CategoryTable from './components/CategoryTable'; // Tu módulo actual

// 🔹 Aquí luego agregarás los demás módulos, ejemplo:
// import DepartmentTable from './components/DepartmentTable';
// import ReportTable from './components/ReportTable';
// import StaffTable from './components/StaffTable';

function App() {
  return (
    <Router>
      <div className="App container mt-4">
        <nav className="mb-4">
          <h3 className="mb-3">Panel de Administración</h3>

          {/* 🔹 Botones de navegación entre módulos */}
          <a href="/category" className="btn btn-primary me-2">Categorías</a>
          {/* 🔹 Aquí agregarás los demás botones */}
          {/* <a href="/department" className="btn btn-secondary me-2">Departamentos</a> */}
          {/* <a href="/report" className="btn btn-info me-2">Reportes</a> */}
          {/* <a href="/staff" className="btn btn-warning me-2">Personal</a> */}
        </nav>

        {/* 🔹 Rutas de navegación */}
        <Routes>
          {/* Página principal */}
          <Route path="/" element={<h4>Bienvenido al panel 🧭</h4>} />

          {/* 🔹 Ruta para categorías */}
          <Route path="/category" element={<CategoryTable />} />

          {/* 🔹 Espacios reservados para tus demás módulos */}
          {/* <Route path="/department" element={<DepartmentTable />} /> */}
          {/* <Route path="/report" element={<ReportTable />} /> */}
          {/* <Route path="/staff" element={<StaffTable />} /> */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
