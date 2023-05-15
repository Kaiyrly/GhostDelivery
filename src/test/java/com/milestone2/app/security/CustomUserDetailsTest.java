package com.milestone2.app.security;

import com.milestone2.app.models.User;
import com.milestone2.app.security.CustomUserDetails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsTest {

    private User mockUser;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        customUserDetails = new CustomUserDetails(mockUser);
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("USER")));
    }

    @Test
    void getPassword() {
        when(mockUser.getPassword()).thenReturn("password123");
        assertEquals("password123", customUserDetails.getPassword());
    }

    @Test
    void getUsername() {
        when(mockUser.getUsername()).thenReturn("testUser");
        assertEquals("testUser", customUserDetails.getUsername());
    }

    @Test
    void getUserId() {
        when(mockUser.getUserId()).thenReturn("userId123");
        assertEquals("userId123", customUserDetails.getUserId());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(customUserDetails.isEnabled());
    }
}
