package com.example.shopsphere.dto;

import java.time.LocalDateTime;

public class OrderResponseDTO {

    private Long orderId;
    private Long userId;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderResponseDTO(
            Long orderId,
            Long userId,
            double totalAmount,
            String status,
            LocalDateTime createdAt) {

        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }
}