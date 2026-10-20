
package com.example.shopsphere.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.shopsphere.exception.ResourceNotFoundException;
import com.example.shopsphere.exception.BadRequestException;
import com.example.shopsphere.model.Cart;
import com.example.shopsphere.model.CartItem;
import com.example.shopsphere.model.Product;
import com.example.shopsphere.repository.CartItemRepository;
import com.example.shopsphere.repository.CartRepository;
import com.example.shopsphere.repository.ProductRepository;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemService(
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            ProductRepository productRepository) {

        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    private Cart getOwnedCart(Long cartId, String email) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));

        if (!cart.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException(
                    "You are not allowed to access this cart");
        }

        return cart;
    }

    public CartItem addToCart(
            String email, Long cartId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new BadRequestException(
                    "Quantity must be greater than 0");
        }

        Cart cart = getOwnedCart(cartId, email);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        CartItem cartItem = new CartItem(cart, product, quantity);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(String email, Long cartId) {

        getOwnedCart(cartId, email);

        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem updateQuantity(
            String email, Long cartId, Long cartItemId, int quantity) {

        if (quantity <= 0) {
            throw new BadRequestException(
                    "Quantity must be greater than 0");
        }

        getOwnedCart(cartId, email);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cartId)) {
            throw new BadRequestException(
                    "Cart item does not belong to this cart");
        }

        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public void removeCartItem(
            String email, Long cartId, Long cartItemId) {

        getOwnedCart(cartId, email);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cartId)) {
            throw new BadRequestException(
                    "Cart item does not belong to this cart");
        }

        cartItemRepository.delete(cartItem);
    }
}