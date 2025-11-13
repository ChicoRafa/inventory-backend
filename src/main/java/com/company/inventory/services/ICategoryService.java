package com.company.inventory.services;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseRest> search();
    ResponseEntity<CategoryResponseRest> searchById(Long id);
    ResponseEntity<CategoryResponseRest> save(Category category);
}
