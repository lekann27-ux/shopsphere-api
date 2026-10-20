package com.example.shopsphere.dto;

public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
