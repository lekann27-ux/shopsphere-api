package com.example.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.shopsphere.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Additional custom query methods can be defined here if needed
    List<CartItem> findByCartId(Long cartId);
}