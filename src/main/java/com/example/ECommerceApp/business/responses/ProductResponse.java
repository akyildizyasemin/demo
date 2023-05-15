package com.example.ECommerceApp.business.responses;
import com.example.ECommerceApp.enums.ProductMaterial;
import com.example.ECommerceApp.enums.RingSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private RingSize ringSize;
    private Map<RingSize, Integer> sizeStockMap;
    private double price;
    private String imageUrl;
    private String details;
    private ProductMaterial productMaterial;
}
