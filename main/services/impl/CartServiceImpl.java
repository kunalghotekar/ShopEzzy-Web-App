package com.sp.ecommers.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ecommers.main.entities.CartItem;
import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.repositories.CartItemRepository;
import com.sp.ecommers.main.services.CartService;

@Service
public class CartServiceImpl
        implements CartService {

    @Autowired
    private CartItemRepository repo;

    @Override
    public CartItem addToCart(
            CartItem cartItem) {

        return repo.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(
            User user) {

        return repo.findByUser(user);
    }
    
    @Override
    public double getCartTotal(User user) {

        List<CartItem> cartItems =
                repo.findByUser(user);

        double total = 0;

        for(CartItem item : cartItems) {

            total +=
                    item.getProduct().getPrice()
                    * item.getQuantity();
        }

        return total;
    }
    
    @Override
    public void updateQuantity(
            Long id,
            int quantity) {

        CartItem cartItem =
                repo.findById(id)
                .orElse(null);

        if(cartItem != null) {

            cartItem.setQuantity(quantity);

            repo.save(cartItem);
        }
    }

    @Override
    public void removeCartItem(Long id) {

        repo.deleteById(id);
    }
}
