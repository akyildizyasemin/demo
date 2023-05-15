package com.example.ECommerceApp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponse {
    private int cartId;
    private List<ShoppingCartProductResponse> products;
    private int totalQuantity;
    private double totalPrice;
}
