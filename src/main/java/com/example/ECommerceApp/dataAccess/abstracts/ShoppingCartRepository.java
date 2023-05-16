package com.example.ECommerceApp.dataAccess.abstracts;

import com.example.ECommerceApp.entities.concretes.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
