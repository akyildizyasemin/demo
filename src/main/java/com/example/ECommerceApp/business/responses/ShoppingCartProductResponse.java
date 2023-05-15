package com.example.ECommerceApp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartProductResponse {
    private int productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double subtotal;
}
