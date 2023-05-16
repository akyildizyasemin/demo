package com.example.ECommerceApp.webApi.controllers;

import com.example.ECommerceApp.business.abstracts.ProductService;
import com.example.ECommerceApp.business.requests.create.CreateProductRequest;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.enums.RingSize;
import com.example.ECommerceApp.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        try {
            List<ProductResponse> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Failed to retrieve all products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int productId) {
        try {
            ProductResponse product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (NotFoundException e) {
            logger.error("Product not found with ID: " + productId, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Failed to retrieve product with ID: " + productId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<ProductResponse> products = productService.getProductsByCategory(categoryName);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Failed to retrieve products by category: " + categoryName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam("keyword") String keyword) {
        try {
            List<ProductResponse> products = productService.searchProducts(keyword);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Failed to search products with keyword: " + keyword, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        try {
            ProductResponse product = productService.createProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            logger.error("Failed to create product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        try {
            productService.deleteProduct(productId);
            logger.info("Product with ID {} deleted successfully", productId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            logger.error("Failed to delete product with ID {}: {}", productId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{productId}/stock")
    public ResponseEntity<Boolean> isProductInStock(@PathVariable int productId, @RequestParam RingSize size) {
        try {
            boolean inStock = productService.isProductInStock(productId, size);
            logger.info("Product with ID {} stock status: {}", productId, inStock);
            return ResponseEntity.ok(inStock);
        } catch (NotFoundException e) {
            logger.error("Failed to check stock for product with ID {}: {}", productId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
