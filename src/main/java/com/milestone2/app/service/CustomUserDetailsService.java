package com.milestone2.app.service;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import com.milestone2.app.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public void updateUser(CustomUserDetails userDetails) {
        User user = userRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Update the necessary user details, such as the password
        user.setPassword(userDetails.getPassword());

        // Save the updated user
        userRepository.save(user);
    }

    @Transactional
    public void changePassword(CustomUserDetails userDetails, String newPassword) {
        User user = userRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Encode the new password before saving
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update the user's password
        user.setPassword(encodedPassword);

        // Save the updated user
        userRepository.save(user);
    }
}
