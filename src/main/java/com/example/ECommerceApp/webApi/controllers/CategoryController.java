package com.example.ECommerceApp.webApi.controllers;
import com.example.ECommerceApp.business.abstracts.CategoryService;
import com.example.ECommerceApp.business.requests.CreateCategoryRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;
import com.example.ECommerceApp.entities.concretes.Category;
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
    public ResponseEntity<?> createCategory(CreateCategoryRequest createCategoryRequest) {
        logger.debug("A new category has been created.");
        this.categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        logger.debug("All categories have been retrieved.");
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

//    @PostMapping("/update/{categoryId}")
//    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId, @RequestBody Category category){
//        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
//    }

}
