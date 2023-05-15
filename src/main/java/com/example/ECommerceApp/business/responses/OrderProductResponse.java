package com.example.ECommerceApp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private int id;
    private ProductResponse product;
    private int quantity;
}
