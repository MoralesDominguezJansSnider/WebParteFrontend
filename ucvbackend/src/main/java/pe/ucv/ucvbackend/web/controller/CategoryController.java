package pe.ucv.ucvbackend.web.controller;

import pe.ucv.ucvbackend.domain.Category;
import pe.ucv.ucvbackend.domain.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ucv")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryList")
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("******************************************");
        logger.info("Category list request accepted successfully.");
        logger.info("******************************************");
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/categorySave")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        logger.info("******************************************");
        logger.info("Category save request accepted successfully.");
        logger.info("******************************************");
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/categoryUpdate/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        logger.info("******************************************");
        logger.info("Category update request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categoryDelete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        logger.info("******************************************");
        logger.info("Category delete request accepted successfully for ID: {}", id);
        logger.info("******************************************");
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}