package com.example.ECommerceApp.business.requests.create;
import com.example.ECommerceApp.enums.RingSize;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class CreateProductRequest {

    private String productName;

    @NotNull
    private Double productPrice;

    @NotNull
    private String productImageUrl;

    @NotNull
    private String productDetails;

    @NotNull
    private Map<RingSize, Integer> sizeStockMap;
    public CreateProductRequest() {
        this.sizeStockMap = new HashMap<>();
    }

}
