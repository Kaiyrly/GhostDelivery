package com.milestone2.app.models;

import com.milestone2.app.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void gettersAndSetters_EnsureCorrectBehavior() {
        // Arrange
        User user = new User();

        // Set values using setters
        String userId = "user1";
        String username = "testuser";
        String password = "password";
        String email = "testuser@example.com";
        Double rating = 4.5;

        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRating(rating);

        // Act & Assert
        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(rating, user.getRating());
    }

    @Test
    void hashCode_SameObject_ReturnsSameHashCode() {
        // Arrange
        User user = new User();
        user.setUserId("1");

        // Act
        int hashCode1 = user.hashCode();
        int hashCode2 = user.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void equals_SameObject_ReturnsTrue() {
        // Arrange
        User user = new User();
        user.setUserId("1");

        // Act
        boolean result = user.equals(user);

        // Assert
        assertTrue(result);
    }

    @Test
    void equals_NullObject_ReturnsFalse() {
        // Arrange
        User user = new User();
        user.setUserId("1");

        // Act
        boolean result = user.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void equals_DifferentClass_ReturnsFalse() {
        // Arrange
        User user = new User();
        user.setUserId("1");

        String differentClass = "Not a User object";

        // Act
        boolean result = user.equals(differentClass);

        // Assert
        assertFalse(result);
    }

    @Test
    void equals_SameUserId_ReturnsTrue() {
        // Arrange
        User user1 = new User();
        user1.setUserId("1");

        User user2 = new User();
        user2.setUserId("1");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    void equals_DifferentUserId_ReturnsFalse() {
        // Arrange
        User user1 = new User();
        user1.setUserId("1");

        User user2 = new User();
        user2.setUserId("2");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result);
    }

    @Test
    void equals_NullUserId_ReturnsFalse() {
        // Arrange
        User user1 = new User();
        user1.setUserId("1");

        User user2 = new User();

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result);
    }
}
