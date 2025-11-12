package pe.ucv.ucvbackend.web.controller;

import pe.ucv.ucvbackend.domain.Department;
import pe.ucv.ucvbackend.domain.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ucv")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/deparmentList")
    public ResponseEntity<List<Department>> getAllDepartments() {
        logger.info("******************************************");
        logger.info("Department list request accepted successfully.");
        logger.info("******************************************");
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/deparmentSave")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        logger.info("******************************************");
        logger.info("Department save request accepted successfully.");
        logger.info("******************************************");
        Department savedDepartment = departmentService.createDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }

    @PutMapping("/deparmentUpdate/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        logger.info("******************************************");
        logger.info("Department update request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/deparmentDelete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.info("******************************************");
        logger.info("Department delete request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }
}