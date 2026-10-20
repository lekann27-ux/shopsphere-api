package com.example.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shopsphere.model.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId); 
    
}
