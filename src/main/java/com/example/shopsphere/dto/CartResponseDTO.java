package com.example.shopsphere.dto;

public class CartResponseDTO {

    private Long cartId;
    private Long userId;

    public Long getCartId() {
        return cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public CartResponseDTO(Long cartId, Long userId) {
        this.cartId = cartId;
        this.userId = userId;
    }
}