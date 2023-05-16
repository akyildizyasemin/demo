package com.example.ECommerceApp.business.requests.create;

import com.example.ECommerceApp.enums.ProductMaterial;
import com.example.ECommerceApp.enums.RingSize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class UpdateProductRequest {
    private int productId;
    @NotNull
    private String productName;

    @NotNull
    private Double productPrice;

    @NotNull
    private String productImageUrl;

    private Map<RingSize, Integer> sizeStockMap;

    private double price;

    @NotNull
    private String productDetails;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductMaterial productMaterial;

    @Enumerated(EnumType.STRING)
    private RingSize ringSize;
}
