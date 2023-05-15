package com.example.ECommerceApp.business.concretes;


import com.example.ECommerceApp.business.abstracts.CategoryService;
import com.example.ECommerceApp.business.requests.CreateCategoryRequest;
import com.example.ECommerceApp.business.requests.UpdateCategoryRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperManager;
import com.example.ECommerceApp.dataAccess.abstracts.CategoryRepository;
import com.example.ECommerceApp.entities.concretes.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapperManager modelMapperService;

    @Override
    public void createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(createCategoryRequest, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(updateCategoryRequest,Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        this.categoryRepository.deleteAll();
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponse> categoriesResponse = categories.stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, CategoryResponse.class)).toList();

        return categoriesResponse;
    }
}
