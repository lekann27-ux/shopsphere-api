
package com.example.shopsphere.service;

import org.springframework.stereotype.Service;

import com.example.shopsphere.exception.ResourceNotFoundException;
import com.example.shopsphere.model.Cart;
import com.example.shopsphere.model.User;
import com.example.shopsphere.repository.CartRepository;
import com.example.shopsphere.repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Cart createCart(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Cart cart = new Cart(user);

        return cartRepository.save(cart);
    }

    public Cart getCartByUserEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return cartRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));
    }
}