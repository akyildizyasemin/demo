package com.example.ECommerceApp.dataAccess.abstracts;

import com.example.ECommerceApp.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
