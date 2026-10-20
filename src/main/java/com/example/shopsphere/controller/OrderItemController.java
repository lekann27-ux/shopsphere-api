package com.example.shopsphere.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.shopsphere.dto.OrderItemResponseDTO;
import com.example.shopsphere.model.OrderItem;
import com.example.shopsphere.service.OrderItemService;

@RestController
@RequestMapping("/api/orders")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    private OrderItemResponseDTO toDTO(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDTO> getOrderItems(
            @PathVariable Long orderId) {

        return orderItemService.getOrderItems(orderId)
                .stream()
                .map(this::toDTO)
                .toList();
    }
}