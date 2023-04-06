package com.milestone1.app.controllers;

import com.milestone1.app.models.User;
import com.milestone1.app.repositories.UserRepository;

import com.milestone1.app.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new CustomException("Invalid userId. Such user does not exist.");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
        User existingUser = userRepository.findByUserId(userId);

        if(existingUser == null) {
            throw new CustomException("Invalid userId. Such user does not exist.");
        }
        
        if (existingUser != null) {
            user.setId(existingUser.getId()); // Preserve the existing ID
        }
        return ResponseEntity.ok(user);
    }
}
