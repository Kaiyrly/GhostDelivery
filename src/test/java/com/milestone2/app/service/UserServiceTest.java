package com.milestone2.app.service;

import com.milestone2.app.models.User;
import com.milestone2.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void testFindUserByUsernameWhenUserExists() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User foundUser = userService.findUserByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    void testFindUserByUsernameWhenUserDoesNotExist() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.findUserByUsername("nonExistentUser");
        });
    }

    @Test
    void testUpdateUserWhenUserExists() {
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setRating(3.5);

        User updatedUser = new User();
        updatedUser.setUsername("testUser");
        updatedUser.setRating(4.0);

        when(userRepository.findByUsername("testUser")).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser("testUser", updatedUser);

        assertNotNull(result);
        assertEquals(4.0, result.getRating());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserWhenUserDoesNotExist() {
        User updatedUser = new User();
        updatedUser.setUsername("nonExistentUser");
        updatedUser.setRating(4.0);

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser("nonExistentUser", updatedUser);
        });

        verify(userRepository, never()).save(any(User.class));
    }
}
