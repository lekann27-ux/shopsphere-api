package com.example.shopsphere.dto;

public class CartItemResponseDTO {

    private Long cartItemId;
    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    public Long getCartItemId() {
        return cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public CartItemResponseDTO(
            Long cartItemId,
            Long productId,
            String productName,
            int quantity,
            double price) {

        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}