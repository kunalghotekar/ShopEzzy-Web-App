package com.sp.ecommers.main.controllers;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sp.ecommers.main.entities.Orders;
import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.services.CartService;
import com.sp.ecommers.main.services.OrderService;
import com.sp.ecommers.main.services.UserService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/placeOrder")
    public String placeOrder(
            Principal principal) {

        String email =
                principal.getName();

        User user =
                userService
                .findByEmail(email)
                .orElse(null);

        Orders order =
                new Orders();

        order.setUser(user);

        order.setOrderDate(
                LocalDateTime.now());

        order.setTotalAmount(
                cartService.getCartTotal(user));

        order.setStatus("PLACED");

        orderService.placeOrder(order);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String ordersPage(
            Model model,
            Principal principal) {

        String email =
                principal.getName();

        User user =
                userService
                .findByEmail(email)
                .orElse(null);

        model.addAttribute(
                "orders",
                orderService.getOrdersByUser(user));

        return "orders";
    }
    
    @GetMapping("/checkout")
    public String checkoutPage(
            Model model,
            Principal principal) {

        String email =
                principal.getName();

        User user =
                userService
                .findByEmail(email)
                .orElse(null);

        model.addAttribute(
                "total",
                cartService.getCartTotal(user));

        return "checkout";
    }
    
   
}