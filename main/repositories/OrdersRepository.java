package com.sp.ecommers.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.ecommers.main.entities.Orders;
import com.sp.ecommers.main.entities.User;

@Repository
public interface OrdersRepository
        extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(User user);

}