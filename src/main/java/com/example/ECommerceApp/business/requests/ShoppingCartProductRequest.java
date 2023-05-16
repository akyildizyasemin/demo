package com.example.ECommerceApp.business.requests;

import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartProductRequest {
    private CreateProductRequest createProductRequest;
    private int quantity;

//    public static ShoppingCartProductRequest from(int productId, int quantity) {
//        ShoppingCartProductRequest request = new ShoppingCartProductRequest();
//        request.setProductId(productId);
//        request.setQuantity(quantity);
//        return request;
//    } servis katmaninda olusturucaz bunuuu
}
