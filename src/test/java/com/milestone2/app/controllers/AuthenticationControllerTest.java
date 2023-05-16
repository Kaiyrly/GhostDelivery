//package com.milestone2.app.controllers;
//
//import com.milestone2.app.models.AuthenticationRequest;
//import com.milestone2.app.models.AuthenticationResponse;
//import com.milestone2.app.models.ChangePasswordRequest;
//import com.milestone2.app.security.CustomUserDetails;
//import com.milestone2.app.service.CustomUserDetailsService;
//import com.milestone2.app.util.JwtUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AuthenticationControllerTest {
//
//    private AuthenticationController authenticationController;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private CustomUserDetailsService userDetailsService;
//
//    @Mock
//    private JwtUtil jwtUtil;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        authenticationController = new AuthenticationController(authenticationManager, userDetailsService, jwtUtil);
//    }
//
//    @Test
//    void createAuthenticationToken_ValidCredentials_ReturnsToken() {
//        // Arrange
//        String username = "testuser";
//        String password = "password";
//        String token = "jwt-token";
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
//        CustomUserDetails userDetails = mock(CustomUserDetails.class);
//
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
//        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
//        when(jwtUtil.generateToken(userDetails)).thenReturn(token);
//
//        // Act
//        ResponseEntity<AuthenticationResponse> response = authenticationController.createAuthenticationToken(authenticationRequest);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(token, response.getBody().getToken());
//        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        verify(userDetailsService, times(1)).loadUserByUsername(username);
//        verify(jwtUtil, times(1)).generateToken(userDetails);
//    }
//
//}