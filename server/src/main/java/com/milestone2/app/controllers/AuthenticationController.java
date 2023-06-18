package com.milestone2.app.controllers;

import com.milestone2.app.models.AuthenticationRequest;
import com.milestone2.app.models.AuthenticationResponse;

import com.milestone2.app.service.CustomUserDetailsService;
import com.milestone2.app.security.CustomUserDetails;
import com.milestone2.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milestone2.app.models.ChangePasswordRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Incorrect username or password");
        }

        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Authenticate the user with the current password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(currentUsername, changePasswordRequest.getCurrentPassword())
        );


        // Get the current user details
        CustomUserDetails currentUser = (CustomUserDetails) userDetailsService.loadUserByUsername(currentUsername);


        // Update the user's password
        userDetailsService.changePassword(currentUser, changePasswordRequest.getNewPassword());

        return ResponseEntity.ok("Password changed successfully");
    }
}
