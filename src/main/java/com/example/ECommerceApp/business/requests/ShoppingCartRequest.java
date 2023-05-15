package com.example.ECommerceApp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequest {
    private int userId;
    private List<ShoppingCartProductRequest> products;
}
