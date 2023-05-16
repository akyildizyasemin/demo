package com.example.ECommerceApp.business.abstracts;

import com.example.ECommerceApp.business.requests.create.CreateProductRequest;
import com.example.ECommerceApp.business.requests.create.UpdateProductRequest;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.enums.RingSize;
import com.example.ECommerceApp.exception.NotFoundException;

import java.util.List;

public interface ProductService {
        List<ProductResponse> getAllProducts();

        ProductResponse getProductById(int productId) throws NotFoundException;

        List<ProductResponse> getProductsByCategory(String categoryName);

        List<ProductResponse> searchProducts(String keyword);

        ProductResponse createProduct(CreateProductRequest request);

        ProductResponse updateProduct(int productId, UpdateProductRequest request) throws NotFoundException;

        void deleteProduct(int productId) throws NotFoundException;

        boolean isProductInStock(int productId, RingSize size);
}
