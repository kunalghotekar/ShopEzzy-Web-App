package com.sp.ecommers.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.services.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute User user,
            Model model) {

        userService.registerUser(user);

        model.addAttribute(
                "successMsg",
                "Registration Successful");

        return "login";
    }
}