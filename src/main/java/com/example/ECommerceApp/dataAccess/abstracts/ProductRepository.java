package com.example.ECommerceApp.dataAccess.abstracts;


import com.example.ECommerceApp.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByProductNameContainingIgnoreCase(String keyword);

}

