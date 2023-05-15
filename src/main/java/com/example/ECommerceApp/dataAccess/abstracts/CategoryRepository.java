package com.example.ECommerceApp.dataAccess.abstracts;


import com.example.ECommerceApp.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
