
package com.example.shopsphere.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.shopsphere.exception.ResourceNotFoundException;
import com.example.shopsphere.exception.BadRequestException;
import com.example.shopsphere.model.Order;
import com.example.shopsphere.model.User;
import com.example.shopsphere.repository.OrderRepository;
import com.example.shopsphere.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order updateStatus(Long orderId, String status) {

        if (status == null || status.isBlank()) {
            throw new BadRequestException("Order status is required");
        }

        if (!status.equals("PENDING")
                && !status.equals("CONFIRMED")
                && !status.equals("SHIPPED")
                && !status.equals("DELIVERED")
                && !status.equals("CANCELLED")) {

            throw new BadRequestException("Invalid order status");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        return saveUpdatedStatus(order, status);
    }

    private Order saveUpdatedStatus(Order order, String status) {
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Order> getMyOrders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return orderRepository.findByUserId(user.getId());
    }

    public Order getMyOrder(String email, Long orderId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException(
                    "You are not allowed to access this order");
        }

        return order;
    }
}