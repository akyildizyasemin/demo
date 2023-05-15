package com.example.ECommerceApp.entities.concretes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "category_id")
    private int categoryId;

    @Column(name = "category_name",nullable = false)
    private String categoryName;

    @OneToMany
    private List<Product> products;

    @Column(name = "category_image")
    private String categoryImageUrl;

}
