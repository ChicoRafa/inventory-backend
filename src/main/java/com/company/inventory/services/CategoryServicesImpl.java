package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements ICategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServicesImpl.class);

    @Autowired
    private ICategoryDao categoryDao;

    /**
     * Fetch all categories
     *
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<Category> categoryList = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(categoryList);
            response.setMetadata("OK", "00", "Categories found");

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while retrieving categories");
            log.error("Error retrieving categories", e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * Fetch category by ID
     *
     * @param id Category ID
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()) {
                categoryList.add(category.get());
                response.getCategoryResponse().setCategory(categoryList);
                response.setMetadata("OK", "00", "Category found");
            } else {
                response.setMetadata("NOT_FOUND", "01", "Category not found with ID: " + id);
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while searching category with ID: " + id);
            log.error("Error searching category with ID: {}", id, e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * Save a new category
     *
     * @param category Category object to be saved
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Category savedCategory = categoryDao.save(category);
            if (savedCategory != null) {
                categoryList.add(savedCategory);
                response.getCategoryResponse().setCategory(categoryList);
                response.setMetadata("OK", "00", "Category saved successfully");
            } else {
                response.setMetadata("ERROR", "02", "Failed to save category");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while saving category");
            log.error("Error saving category", e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.CREATED);
    }

    /**
     * Update an existing category by ID
     *
     * @param category Category object with updated data
     * @param id       ID of the category to be updated
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> existingCategoryOpt = categoryDao.findById(id);
            if (existingCategoryOpt.isPresent()) {
                Category existingCategory = existingCategoryOpt.get();
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());

                Category updatedCategory = categoryDao.save(existingCategory);
                categoryList.add(updatedCategory);
                response.getCategoryResponse().setCategory(categoryList);
                response.setMetadata("OK", "00", "Category updated successfully");
            } else {
                response.setMetadata("NOT_FOUND", "01", "Category not found with ID: " + id);
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while updating category with ID: " + id);
            log.error("Error updating category with ID: {}", id, e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * Delete category by ID
     *
     * @param id Category ID
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            if (categoryDao.existsById(id)) {
                categoryDao.deleteById(id);
                response.setMetadata("OK", "00", "Category deleted successfully");
            } else {
                response.setMetadata("NOT_FOUND", "01", "Category not found with ID: " + id);
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while deleting category with ID: " + id);
            log.error("Error deleting category with ID: {}", id, e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * Delete all categories
     *
     * @return ResponseEntity containing CategoryResponseRest
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteAll() {
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            categoryDao.deleteAll();
            response.setMetadata("OK", "00", "All categories deleted successfully");

        } catch (Exception e) {
            response.setMetadata("ERROR", "-1", "Database error while deleting all categories");
            log.error("Error deleting all categories", e);
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
