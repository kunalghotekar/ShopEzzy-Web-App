package com.sp.ecommers.main.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.services.UserService;

@Controller
public class HomeController {

	 @Autowired
	    private UserService userService;

	    @GetMapping("/")
	    public String homePage(
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

	        return "home";
	    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @GetMapping("/403")
    public String accessDeniedPage() {

        return "error/403";
    }
    
}