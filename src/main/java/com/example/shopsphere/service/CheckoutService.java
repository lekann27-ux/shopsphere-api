
package com.example.shopsphere.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopsphere.exception.ResourceNotFoundException;
import com.example.shopsphere.exception.BadRequestException;
import com.example.shopsphere.model.Cart;
import com.example.shopsphere.model.CartItem;
import com.example.shopsphere.model.Order;
import com.example.shopsphere.model.OrderItem;
import com.example.shopsphere.model.User;
import com.example.shopsphere.repository.CartItemRepository;
import com.example.shopsphere.repository.CartRepository;
import com.example.shopsphere.repository.OrderItemRepository;
import com.example.shopsphere.repository.OrderRepository;
import com.example.shopsphere.repository.UserRepository;

@Service
public class CheckoutService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    public CheckoutService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order checkout(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));

        List<CartItem> cartItems =
                cartItemRepository.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        double totalAmount = 0;

        for (CartItem cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice()
                    * cartItem.getQuantity();
        }

        Order order = new Order(
                cart.getUser(),
                totalAmount,
                "PENDING");

        order = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(
                    order,
                    cartItem.getProduct(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteAll(cartItems);

        return order;
    }
}