
package com.example.shopsphere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.shopsphere.dto.CartResponseDTO;
import com.example.shopsphere.model.Cart;
import com.example.shopsphere.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    private CartResponseDTO toDTO(Cart cart) {
        return new CartResponseDTO(
                cart.getId(),
                cart.getUser().getId()
        );
    }

    @PostMapping
    public CartResponseDTO createCart(Authentication authentication) {
        Cart cart = cartService.createCart(authentication.getName());
        return toDTO(cart);
    }

    @GetMapping("/my-cart")
    public CartResponseDTO getMyCart(Authentication authentication) {
        Cart cart = cartService.getCartByUserEmail(authentication.getName());
        return toDTO(cart);
    }
}