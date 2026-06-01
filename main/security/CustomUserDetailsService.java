package com.sp.ecommers.main.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sp.ecommers.main.entities.User;
import com.sp.ecommers.main.repositories.UserRepository;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        Optional<User> optionalUser =
                repo.findByEmail(email);

        if(optionalUser.isPresent()) {

            User user = optionalUser.get();

            return org.springframework.security
                    .core.userdetails.User
                    .builder()

                    .username(user.getEmail())

                    .password(user.getPassword())

                    .roles(user.getRole())

                    .build();
        }

        throw new UsernameNotFoundException(
                "User not found");
    }
}