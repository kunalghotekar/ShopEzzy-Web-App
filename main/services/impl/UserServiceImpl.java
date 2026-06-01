package com.sp.ecommers.main.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.repositories.UserRepository;
import com.sp.ecommers.main.services.UserService;

@Service
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User registerUser(User user) {

        user.setPassword(
                encoder.encode(
                        user.getPassword()));

        user.setRole("USER");

        return repo.save(user);
    }

    @Override
    public Optional<User> findByEmail(
            String email) {

        return repo.findByEmail(email);
    }

	
}