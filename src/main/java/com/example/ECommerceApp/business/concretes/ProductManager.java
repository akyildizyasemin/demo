package com.example.ECommerceApp.business.concretes;

import com.example.ECommerceApp.business.abstracts.ProductService;
import com.example.ECommerceApp.business.requests.create.CreateProductRequest;
import com.example.ECommerceApp.business.requests.create.UpdateProductRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperService;
import com.example.ECommerceApp.dataAccess.abstracts.ProductRepository;
import com.example.ECommerceApp.entities.concretes.Product;
import com.example.ECommerceApp.enums.RingSize;
import com.example.ECommerceApp.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = modelMapperService.forResponse().map(product, ProductResponse.class);
                    CategoryResponse categoryResponse = modelMapperService.forResponse().map(product.getCategory(), CategoryResponse.class);
                    productResponse.setCategoryResponse(categoryResponse);
                    return productResponse;
                })
                .toList();
    }

    @Override
    public ProductResponse getProductById(int productId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));

        return modelMapperService.forResponse().map(product, ProductResponse.class);
    }



    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream()
                .map(product -> modelMapperService.forResponse().map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(keyword);
        return products.stream()
                .map(product -> modelMapperService.forResponse().map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        // Map the CreateProductRequest to Product entity
        Product product = modelMapperService.forRequest().map(request, Product.class);

        // Save the product in the database
        Product savedProduct = productRepository.save(product);

        // Map the saved product to ProductResponse
        ProductResponse productResponse = modelMapperService.forResponse().map(savedProduct, ProductResponse.class);

        return productResponse;
    }


    @Override
    public ProductResponse updateProduct(int productId, UpdateProductRequest request) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));

        // Güncelleme yapılacak alanları request üzerinden alarak product nesnesini güncelle

        productRepository.save(product);  // Güncellenen ürünü veritabanına kaydet

        return modelMapperService.forResponse().map(product, ProductResponse.class);
    }

    @Override
    public void deleteProduct(int productId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));

        // Ürünü silmek için gerekli işlemleri gerçekleştir

        productRepository.delete(product);  // Ürünü veritabanından sil
    }


    @Override
    public boolean isProductInStock(int productId, RingSize size) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));

        Map<RingSize, Integer> sizeStockMap = product.getSizeStockMap();

        if (sizeStockMap.containsKey(size)) {
            int stockQuantity = sizeStockMap.get(size);
            return stockQuantity > 0;
        }
        return false;
    }

}

