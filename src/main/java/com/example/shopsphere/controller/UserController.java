package com.example.shopsphere.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopsphere.service.UserService;
import com.example.shopsphere.dto.UserResponseDTO;
import com.example.shopsphere.exception.ResourceNotFoundException;
import com.example.shopsphere.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add methods to handle user-related requests, such as creating a user, retrieving user details, updating user information, etc.
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()))
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    @GetMapping("/email")
    public UserResponseDTO getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        return new UserResponseDTO(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail());
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);

        return new UserResponseDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail());
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
