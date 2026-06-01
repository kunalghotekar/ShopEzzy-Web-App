package com.sp.ecommers.main.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import com.sp.ecommers.main.entities.CartItem;
import com.sp.ecommers.main.entities.Product;
import com.sp.ecommers.main.entities.User;

import com.sp.ecommers.main.services.CartService;
import com.sp.ecommers.main.services.ProductService;
import com.sp.ecommers.main.services.UserService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // VIEW CART

    @GetMapping("/cart")
    public String cartPage(
            Model model,
            Principal principal) {

        String email =
                principal.getName();

        User user =
                userService
                .findByEmail(email)
                .orElse(null);

        model.addAttribute(
                "cartItems",
                cartService.getCartItems(user));
        
        model.addAttribute(
                "total",
                cartService.getCartTotal(user));

        return "cart";
    }

    // ADD TO CART

    @PostMapping("/addToCart/{id}")
    public String addToCart(
            @PathVariable Long id,
            Principal principal) {      //gets currently logged-in user.

        String email =
                principal.getName();

        User user =
                userService
                .findByEmail(email)
                .orElse(null);

        Product product =
                productService
                .getProductById(id);

        CartItem cartItem =
                new CartItem();

        cartItem.setUser(user);

        cartItem.setProduct(product);

        cartItem.setQuantity(1);

        cartService.addToCart(cartItem);

        return "redirect:/cart";
    }
    
    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(
            @PathVariable Long id,

            @RequestParam int quantity) {

        cartService.updateQuantity(id, quantity);

        return "redirect:/cart";
    }

    // REMOVE ITEM

    @GetMapping("/removeCartItem/{id}")
    public String removeCartItem(
            @PathVariable Long id) {

        cartService.removeCartItem(id);

        return "redirect:/cart";
    }
}