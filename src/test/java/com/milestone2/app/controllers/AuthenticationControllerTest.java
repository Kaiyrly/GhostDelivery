//package com.milestone2.app.controllers;
//
//import com.milestone2.app.models.AuthenticationRequest;
//import com.milestone2.app.models.AuthenticationResponse;
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
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class AuthenticationControllerTest {
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
//    private AuthenticationController authenticationController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        authenticationController = new AuthenticationController();
//        authenticationController.setAuthenticationManager(authenticationManager);
//        authenticationController.setUserDetailsService(userDetailsService);
//        authenticationController.setJwtUtil(jwtUtil);
//    }
//
//    @Test
//    void testCreateAuthenticationTokenWithValidCredentials() {
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
//        authenticationRequest.setUsername("testUser");
//        authenticationRequest.setPassword("testPassword");
//
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
//
//        CustomUserDetails userDetails = new CustomUserDetails();
//        userDetails.setUsername("testUser");
//        userDetails.setPassword("testPassword");
//        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
//
//        String jwt = "testToken";
//        when(jwtUtil.generateToken(userDetails)).thenReturn(jwt);
//
//        ResponseEntity<AuthenticationResponse> response = authenticationController.createAuthenticationToken(authenticationRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(jwt, response.getBody().getJwt());
//    }
//
//    @Test
//    void testCreateAuthenticationTokenWithInvalidCredentials() {
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
//        authenticationRequest.setUsername("testUser");
//        authenticationRequest.setPassword("testPassword");
//
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenThrow(BadCredentialsException.class);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            authenticationController.createAuthenticationToken(authenticationRequest);
//        });
//
//        verify(userDetailsService, never()).loadUserByUsername(anyString());
//        verify(jwtUtil, never()).generateToken(any(CustomUserDetails.class));
//    }
//}
