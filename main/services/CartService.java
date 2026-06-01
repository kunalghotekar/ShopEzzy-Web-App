package com.sp.ecommers.main.services;

import java.util.List;

import com.sp.ecommers.main.entities.CartItem;
import com.sp.ecommers.main.entities.User;

public interface CartService {

    CartItem addToCart(
            CartItem cartItem);

    List<CartItem> getCartItems(
            User user);

    void removeCartItem(Long id);
    
    double getCartTotal(User user);
    
    void updateQuantity( Long id, int quantity);
}