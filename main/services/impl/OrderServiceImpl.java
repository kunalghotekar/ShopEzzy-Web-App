package com.sp.ecommers.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ecommers.main.entities.Orders;
import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.repositories.OrdersRepository;
import com.sp.ecommers.main.services.OrderService;

@Service
public class OrderServiceImpl
        implements OrderService {

    @Autowired
    private OrdersRepository repo;

    @Override
    public Orders placeOrder(
            Orders order) {

        return repo.save(order);
    }

    @Override
    public List<Orders> getOrdersByUser(
            User user) {

        return repo.findByUser(user);
    }
}