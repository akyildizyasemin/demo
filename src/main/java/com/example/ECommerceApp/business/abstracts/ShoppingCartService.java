package com.example.ECommerceApp.business.abstracts;

import com.example.ECommerceApp.business.requests.UpdateShoppingCartProductRequest;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartResponse;



public interface ShoppingCartService {
    void addProductToCart(int userId, ProductResponse productResponse, int quantity);
    void removeProductFromCart(int userId, ShoppingCartProductResponse shoppingCartProductResponse);
    void updateShoppingCartProductQuantity(int userId, UpdateShoppingCartProductRequest request, int quantity);
    void clearCart(int userId);
    ShoppingCartResponse getShoppingCart(int userId);

}