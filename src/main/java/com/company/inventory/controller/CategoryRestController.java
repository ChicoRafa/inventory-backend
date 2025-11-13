package com.company.inventory.controller;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * Fetch all categories
     *
     * @return ResponseEntity containing CategoryResponseRest
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }

    /**
     * Fetch category by ID
     *
     * @param id Category ID
     * @return ResponseEntity containing CategoryResponseRest
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }

    /**
     * Save a new category
     *
     * @param category Category object to be saved
     * @return ResponseEntity containing CategoryResponseRest
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }

    /**
     * Update an existing category
     *
     * @param category Category object with updated data
     * @param id       Category ID to be updated
     * @return ResponseEntity containing CategoryResponseRest
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
        return response;
    }

    /**
     * Delete category by ID
     *
     * @param id Category ID to be deleted
     * @return ResponseEntity containing CategoryResponseRest
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> deleteCategoryById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }

    /**
     * Delete all categories
     *
     * @return ResponseEntity containing CategoryResponseRest
     */
    @DeleteMapping("/categories")
    public ResponseEntity<CategoryResponseRest> deleteAllCategories() {
        ResponseEntity<CategoryResponseRest> response = categoryService.deleteAll();
        return response;
    }
}
