package com.milestone2.app.controllers;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RatingControllerTest {

    private RatingController ratingController;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        ratingController = new RatingController(userRepository);
    }

    @Test
    public void testUpdateUserRating() {
        User user = new User();
        user.setRating(4.0);
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<String> responseEntity = ratingController.updateUserRating("123", 5.0);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User rating updated successfully", responseEntity.getBody());
        assertEquals(4.5, user.getRating());

        verify(userRepository, times(1)).findById("123");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserRating_UserNotFound() {
        when(userRepository.findById("123")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ratingController.updateUserRating("123", 5.0));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById("123");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testGetUserRating() {
        User user = new User();
        user.setRating(4.5);
        when(userRepository.findById("123")).thenReturn(Optional.of(user));

        ResponseEntity<Double> responseEntity = ratingController.getUserRating("123", mock(Authentication.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(4.5, responseEntity.getBody());

        verify(userRepository, times(1)).findById("123");
    }

    @Test
    public void testGetUserRating_UserNotFound() {
        when(userRepository.findById("123")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ratingController.getUserRating("123", mock(Authentication.class)));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById("123");
    }
}
