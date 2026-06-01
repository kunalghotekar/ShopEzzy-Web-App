package com.sp.ecommers.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

            // AUTHORIZE REQUESTS

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/",
                        "/register",
                        "/login",
                        "/css/**",
                        "/images/**")
                .permitAll()
                
                // CART URLS REQUIRE LOGIN

                .requestMatchers("/cart/**")
                .authenticated()

                .requestMatchers(
                        "/addProduct",
                        "/saveProduct",
                        "/editProduct/**",
                        "/deleteProduct/**")
                .hasRole("ADMIN")

                .anyRequest()
                .authenticated()
            )
            
            .exceptionHandling(exception -> exception

            	    .accessDeniedPage("/403")
            	)

            // LOGIN CONFIGURATION

            .formLogin(form -> form

                .loginPage("/login")

                .loginProcessingUrl("/login")

                .defaultSuccessUrl("/products")

                .failureUrl("/login?error=true")

                .permitAll()
            )

            // LOGOUT CONFIGURATION

            .logout(logout -> logout

                .logoutUrl("/logout")

                .logoutSuccessUrl("/login?logout=true")

                .permitAll()
            )

            // CSRF

            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
}