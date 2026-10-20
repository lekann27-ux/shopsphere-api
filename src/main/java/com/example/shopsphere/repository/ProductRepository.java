package com.example.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopsphere.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be defined here if needed
    
}
