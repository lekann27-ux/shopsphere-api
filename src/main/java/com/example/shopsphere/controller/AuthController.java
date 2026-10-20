package com.example.shopsphere.controller;

//import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopsphere.dto.LoginRequest;
import com.example.shopsphere.exception.UnauthorizedException;
import com.example.shopsphere.service.UserService;
import com.example.shopsphere.model.User;
import com.example.shopsphere.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail())
                .orElseThrow(()
                        -> new UnauthorizedException("Invalid email or password"));

        if (!userService.checkPassword(
                request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail(), user.getRole());
    }
}
