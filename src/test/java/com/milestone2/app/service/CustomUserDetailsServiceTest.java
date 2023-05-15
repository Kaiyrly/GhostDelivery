package com.milestone2.app.service;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import com.milestone2.app.security.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new CustomUserDetailsService(userRepository, passwordEncoder);
    }

    @Test
    void testLoadUserByUsername_ExistingUser_ReturnsCustomUserDetails() {
        User user = new User();
        user.setUserId("123");
        user.setUsername("testUser");
        user.setPassword("password");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals(user.getUserId(), userDetails.getUserId());
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());

        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void testLoadUserByUsername_NonExistingUser_ThrowsUsernameNotFoundException() {
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("testUser");
        });

        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void testUpdateUser_ExistingUser_UserUpdated() {
        User existingUser = new User();
        existingUser.setUserId("123");
        existingUser.setUsername("testUser");
        existingUser.setPassword("password");

        CustomUserDetails userDetails = new CustomUserDetails(existingUser);

        when(userRepository.findById("123")).thenReturn(java.util.Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        assertDoesNotThrow(() -> {
            userDetailsService.updateUser(userDetails);
        });

        verify(userRepository, times(1)).findById("123");
        verify(userRepository, times(1)).save(existingUser);
    }

//    @Test
//    void testUpdateUser_NonExistingUser_ThrowsUsernameNotFoundException() {
//        CustomUserDetails userDetails = new CustomUserDetails();
//        userDetails.setUserId("123");
//        userDetails.setUsername("testUser");
//        userDetails.setPassword("password");
//
//        when(userRepository.findById("123")).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> {
//            userDetailsService.updateUser(userDetails);
//        });
//
//        verify(userRepository, times(1)).findById("123");
//        verify(userRepository, never()).save(any(User.class));
//    }

    @Test
    void testChangePassword_ExistingUser_PasswordChanged() {
        User existingUser = new User();
        existingUser.setUserId("123");
        existingUser.setUsername("testUser");
        existingUser.setPassword("password");

        CustomUserDetails userDetails = new CustomUserDetails(existingUser);

        String newPassword = "newPassword";
        String encodedPassword = "encodedPassword";

        when(userRepository.findById("123")).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(newPassword)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        assertDoesNotThrow(() -> {
            userDetailsService.changePassword(userDetails, newPassword);
        });

        assertEquals(encodedPassword, existingUser.getPassword());

        verify(userRepository, times(1)).findById("123");
        verify(passwordEncoder, times(1)).encode(newPassword);
        verify(userRepository, times(1)).save(existingUser);
    }

}