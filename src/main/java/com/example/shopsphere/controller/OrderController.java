
package com.example.shopsphere.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.shopsphere.dto.OrderResponseDTO;
import com.example.shopsphere.model.Order;
import com.example.shopsphere.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    @GetMapping("/my-orders")
    public List<OrderResponseDTO> getMyOrders(
            Authentication authentication) {

        return orderService.getMyOrders(authentication.getName())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getMyOrder(
            @PathVariable Long orderId,
            Authentication authentication) {

        Order order = orderService.getMyOrder(
                authentication.getName(), orderId);

        return toDTO(order);
    }

    @PutMapping("/{orderId}/status")
    public OrderResponseDTO updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        Order order = orderService.updateStatus(orderId, status);

        return toDTO(order);
    }
}