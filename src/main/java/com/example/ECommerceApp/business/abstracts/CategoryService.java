package com.example.ECommerceApp.business.abstracts;


import com.example.ECommerceApp.business.requests.CreateCategoryRequest;
import com.example.ECommerceApp.business.requests.UpdateCategoryRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void createCategory(CreateCategoryRequest createCategoryRequest);
    void updateCategory(UpdateCategoryRequest updateCategoryRequest);
    void deleteCategoryById(int id);
    void deleteAllCategories();

    List<CategoryResponse> getAll();

}
