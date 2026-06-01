package com.sp.ecommers.main.services;

import java.util.Optional;

import com.sp.ecommers.main.entities.User;

public interface UserService {

    User registerUser(User user);

    Optional<User> findByEmail(String email);

}