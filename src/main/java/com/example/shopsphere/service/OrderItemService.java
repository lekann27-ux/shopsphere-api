package com.example.shopsphere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopsphere.model.OrderItem;
import com.example.shopsphere.repository.OrderItemRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}