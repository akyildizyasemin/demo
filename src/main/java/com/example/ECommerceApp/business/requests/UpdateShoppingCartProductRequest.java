package com.example.ECommerceApp.business.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShoppingCartProductRequest {

    @NotNull
    private int productId;

    private int quantity;

}
