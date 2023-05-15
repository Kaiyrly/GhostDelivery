package com.milestone2.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.milestone2.app.repositories.UserRepository;

@Component
public class SharedServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }
}

