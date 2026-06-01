package com.sp.ecommers.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.ecommers.main.entities.CartItem;
import com.sp.ecommers.main.entities.User;

@Repository
public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser(User user);

}