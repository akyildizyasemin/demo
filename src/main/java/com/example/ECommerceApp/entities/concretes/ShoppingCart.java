package com.example.ECommerceApp.entities.concretes;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_shopping_cart")
public class ShoppingCart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToMany
    private List<ShoppingCartProduct> shoppingCartProducts;

//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

}

