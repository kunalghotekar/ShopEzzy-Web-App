package com.sp.ecommers.main.services;

import java.util.List;

import com.sp.ecommers.main.entities.Orders;
import com.sp.ecommers.main.entities.User;

public interface OrderService {

	Orders placeOrder(Orders order);

    List<Orders> getOrdersByUser(
            User user);
}
