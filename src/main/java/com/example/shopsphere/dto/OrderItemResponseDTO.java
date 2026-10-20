package com.example.shopsphere.dto;

public class OrderItemResponseDTO {

    private Long orderItemId;
    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    public Long getOrderItemId() {
        return orderItemId;
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

    public OrderItemResponseDTO(
            Long orderItemId,
            Long productId,
            String productName,
            int quantity,
            double price) {

        this.orderItemId = orderItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}