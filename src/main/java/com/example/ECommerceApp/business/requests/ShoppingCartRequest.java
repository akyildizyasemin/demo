package com.example.ECommerceApp.business.requests;

import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequest {
    private int userId;
    private CreateProductRequest createProductRequest;
    private List<ShoppingCartProductRequest> products;
    private int quantity;

    public ShoppingCartRequest(CreateProductRequest createProductRequest, int quantity) {
        this.createProductRequest = createProductRequest;
        this.quantity = quantity;
    }
}
