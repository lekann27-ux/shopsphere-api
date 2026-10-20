
package com.example.shopsphere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.shopsphere.dto.OrderResponseDTO;
import com.example.shopsphere.model.Order;
import com.example.shopsphere.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public OrderResponseDTO checkout(Authentication authentication) {

        Order order = checkoutService.checkout(authentication.getName());

        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}