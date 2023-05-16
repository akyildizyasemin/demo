package com.example.ECommerceApp.dataAccess.abstracts;

import com.example.ECommerceApp.entities.concretes.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
