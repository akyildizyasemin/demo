package com.example.ECommerceApp.dataAccess.abstracts;

import com.example.ECommerceApp.entities.concretes.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct,Integer> {
}
