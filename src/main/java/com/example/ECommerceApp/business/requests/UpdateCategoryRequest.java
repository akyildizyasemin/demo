package com.example.ECommerceApp.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

    @NotNull
    private int id;

    @NotNull
    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 3, max =20)
    private String name;

    private String categoryImageUrl;

}
