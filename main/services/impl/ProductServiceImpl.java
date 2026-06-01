package com.sp.ecommers.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ecommers.main.entities.Product;
import com.sp.ecommers.main.repositories.ProductRepository;
import com.sp.ecommers.main.services.ProductService;

@Service
public class ProductServiceImpl
        implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public Product saveProduct(
            Product product) {

        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {

        return repo.findById(id)
                .orElse(null);
    }

    @Override
    public Product updateProduct(
            Long id,
            Product product) {

        Product existingProduct =
                repo.findById(id).orElse(null);

        if(existingProduct != null) {

            existingProduct.setName(
                    product.getName());

            existingProduct.setDescription(
                    product.getDescription());

            existingProduct.setPrice(
                    product.getPrice());

            existingProduct.setQuantity(
                    product.getQuantity());

            return repo.save(existingProduct);
        }

        return null;
    }

    @Override
    public void deleteProduct(Long id) {

        repo.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(
            String keyword) {

        return repo.findByNameContaining(
                keyword);
    }
}