package com.milestone2.app.util;

import com.milestone2.app.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JwtUtilTest {

    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    CustomUserDetails customUserDetails;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtil, "secret", "testSecret");
    }

    @Test
    public void generateTokenTest() {
        when(customUserDetails.getUsername()).thenReturn("testUsername");
        String token = jwtUtil.generateToken(customUserDetails);
        assertNotNull(token);
    }

    @Test
    public void validateTokenTest() {
        when(customUserDetails.getUsername()).thenReturn("testUsername");
        String token = jwtUtil.generateToken(customUserDetails);
        boolean isValid = jwtUtil.validateToken(token, customUserDetails);
        assertTrue(isValid);
    }

    @Test
    public void validateTokenWithUsernameTest() {
        when(customUserDetails.getUsername()).thenReturn("testUsername");
        String token = jwtUtil.generateToken(customUserDetails);
        boolean isValid = jwtUtil.validateToken(token, "testUsername");
        assertTrue(isValid);
    }

    @Test
    public void extractUsernameTest() {
        when(customUserDetails.getUsername()).thenReturn("testUsername");
        String token = jwtUtil.generateToken(customUserDetails);
        String username = jwtUtil.extractUsername(token);
        assertEquals("testUsername", username);
    }

}
