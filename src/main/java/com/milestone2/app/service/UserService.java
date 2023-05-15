package com.milestone2.app.service;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    
    @Transactional
    public User updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            existingUser.setRating(updatedUser.getRating());

            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
        return existingUser;
    }
}
