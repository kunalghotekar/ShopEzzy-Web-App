package com.sp.ecommers.main.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.Model;

import com.sp.ecommers.main.entities.User;

import com.sp.ecommers.main.services.UserService;
                                     // this class created for share the navbar user name to all pages
@ControllerAdvice
public class GlobalControllerAdvice {  

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUser(
            Model model,
            Principal principal) {

        if(principal != null) {

            String email =
                    principal.getName();

            User user =
                    userService
                    .findByEmail(email)
                    .orElse(null);

            if(user != null) {

                model.addAttribute(
                        "username",
                        user.getName());
            }
        }
    }
}