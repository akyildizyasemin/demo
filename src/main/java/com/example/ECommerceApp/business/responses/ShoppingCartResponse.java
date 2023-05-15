package com.example.ECommerceApp.business.responses;

import java.util.List;

public class ShoppingCartResponse {
    private int cartId;
    private List<ShoppingCartProductResponse> products;
    private int totalQuantity;
    private double totalPrice;
}
