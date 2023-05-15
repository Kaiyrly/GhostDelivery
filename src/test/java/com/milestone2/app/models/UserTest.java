package com.milestone2.app.models;

import com.milestone2.app.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    void testGettersAndSetters() {
        // Create a sample user
        User user = new User();
        user.setUserId("123");
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setRating(4.5);

        // Test the getters
        assertEquals("123", user.getUserId());
        assertEquals("testUser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals(4.5, user.getRating());

        // Test the setters
        user.setId("456");
        assertEquals("456", user.getId());

        user.setUserId("789");
        assertEquals("789", user.getUserId());

        user.setRating(3.5);
        assertEquals(3.5, user.getRating());

        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }
}
