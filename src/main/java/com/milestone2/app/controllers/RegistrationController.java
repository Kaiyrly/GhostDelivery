package com.milestone2.app.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.milestone2.app.repositories.UserRepository;
import com.milestone2.app.security.CustomUserDetails;
import com.milestone2.app.util.JwtUtil;
import com.milestone2.app.models.User;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        // Check if username already exists
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); 
        }

        // Encode password and save user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(new CustomUserDetails(savedUser));

        // Prepare the response with the JWT token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        // Return the JWT token in the response
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

