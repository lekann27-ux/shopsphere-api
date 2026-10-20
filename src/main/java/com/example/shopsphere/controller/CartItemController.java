
package com.example.shopsphere.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.shopsphere.dto.CartItemResponseDTO;
import com.example.shopsphere.model.CartItem;
import com.example.shopsphere.service.CartItemService;

@RestController
@RequestMapping("/api/carts")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    private CartItemResponseDTO toDTO(CartItem cartItem) {
        return new CartItemResponseDTO(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice()
        );
    }

    @PostMapping("/{cartId}/product/{productId}")
    public CartItemResponseDTO addToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int quantity,
            Authentication authentication) {

        CartItem cartItem = cartItemService.addToCart(
                authentication.getName(), cartId, productId, quantity);

        return toDTO(cartItem);
    }

    @GetMapping("/{cartId}/cart-items")
    public List<CartItemResponseDTO> getCartItems(
            @PathVariable Long cartId,
            Authentication authentication) {

        return cartItemService.getCartItems(
                        authentication.getName(), cartId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @PutMapping("/{cartId}/cart-item/{cartItemId}")
    public CartItemResponseDTO updateQuantity(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId,
            @RequestParam int quantity,
            Authentication authentication) {

        CartItem cartItem = cartItemService.updateQuantity(
                authentication.getName(), cartId, cartItemId, quantity);

        return toDTO(cartItem);
    }

    @DeleteMapping("/{cartId}/cart-item/{cartItemId}")
    public String removeCartItem(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId,
            Authentication authentication) {

        cartItemService.removeCartItem(
                authentication.getName(), cartId, cartItemId);

        return "Cart item removed successfully";
    }
}