package com.sp.ecommers.main.services;

import java.util.List;

import com.sp.ecommers.main.entities.Product;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(
            Long id,
            Product product);

    void deleteProduct(Long id);

    List<Product> searchProducts(
            String keyword);
}