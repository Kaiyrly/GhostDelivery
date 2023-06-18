package com.milestone2.app.controllers;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    public RatingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}/{rating}")
    public ResponseEntity<String> updateUserRating(
            @PathVariable String userId,
            @PathVariable double rating
    ) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Double updatedRating = (userToUpdate.getRating() + rating) / 2;
        userToUpdate.setRating(updatedRating);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok("User rating updated successfully");
    }


    @GetMapping("/{userId}/rating")
    public ResponseEntity<Double> getUserRating(
            @PathVariable String userId,
            Authentication authentication
    ) {
        // Retrieve the user to get the rating
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LOGGER.info("Retrieved rating {} for user with ID: {}", user.getRating(), userId);

        return ResponseEntity.ok(user.getRating());
    }
}
