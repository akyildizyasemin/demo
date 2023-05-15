package com.example.ECommerceApp.webApi.controllers;

import com.example.ECommerceApp.business.abstracts.CategoryService;
import com.example.ECommerceApp.business.requests.CreateCategoryRequest;
import com.example.ECommerceApp.business.requests.UpdateCategoryRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        logger.info("Creating a new category...");

        try {
            categoryService.createCategory(createCategoryRequest);
            logger.info("Category created successfully");
            return ResponseEntity.ok("Category created successfully");
        } catch (Exception e) {
            logger.error("Failed to create category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create category");
        }
    }
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        logger.debug("All categories have been retrieved.");
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        try {
            categoryService.updateCategory(updateCategoryRequest);
            logger.info("Category updated successfully.");
            return ResponseEntity.ok("Category updated successfully.");
        } catch (Exception e) {
            logger.error("Failed to update category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update category.");
        }
    }
}
