package com.sp.ecommers.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ecommers.main.entities.Product;
import com.sp.ecommers.main.services.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public String getAllProducts(
            Model model) {

        model.addAttribute(
                "products",
                service.getAllProducts());

        return "products";
    }

    @GetMapping("/addProduct")
    public String addProductPage(
            Model model) {

        model.addAttribute(
                "product",
                new Product());

        return "add-product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(
            @ModelAttribute Product product) {

        service.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "product",
                service.getProductById(id));

        return "edit-product";
    }
    
    @GetMapping("/search")
    public String searchProducts(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "products",
                service.searchProducts(keyword));

        return "products";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product) {

        service.updateProduct(id, product);

        return "redirect:/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return "redirect:/products";
    }
}