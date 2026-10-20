package com.example.shopsphere.dto;

public class ProductResponseDTO {

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    private Long productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public ProductResponseDTO(
            Long productId,
            String name,
            String description,
            double price,
            int quantity) {

        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}