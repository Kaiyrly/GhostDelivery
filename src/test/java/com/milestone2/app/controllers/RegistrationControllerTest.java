//package com.milestone2.app.controllers;
//
//import com.milestone2.app.controllers.RegistrationController;
//import com.milestone2.app.models.User;
//import com.milestone2.app.repositories.UserRepository;
//import com.milestone2.app.util.JwtUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class RegistrationControllerTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private JwtUtil jwtUtil;
//
//    @InjectMocks
//    private RegistrationController registrationController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegister_Success() {
//        // Prepare test data
//        User user = new User();
//        user.setUsername("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
//        when(userRepository.findByUsername(user.getEmail())).thenReturn(null);
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(user)).thenReturn(user);
////        when(jwtUtil.generateToken(any(CustomUserDetails.class))).thenReturn("jwtToken");
//
//        // Execute the register method
//        ResponseEntity<Map<String, String>> response = registrationController.register(user);
//
//        // Verify the response
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        // Verify the token in the response
//        Map<String, String> responseBody = response.getBody();
//        assertNotNull(responseBody);
//        assertTrue(responseBody.containsKey("token"));
//        assertEquals("jwtToken", responseBody.get("token"));
//
//        // Verify the save method is called
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testRegister_Conflict_UsernameExists() {
//        // Prepare test data
//        User user = new User();
//        user.setUsername("existinguser");
//        user.setEmail("newuser@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
//
//        // Execute the register method
//        ResponseEntity<Map<String, String>> response = registrationController.register(user);
//
//        // Verify the response
//        assertNotNull(response);
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//
//        // Verify the save method is not called
//        verify(userRepository, never()).save(user);
//    }
//
//    @Test
//    public void testRegister_Conflict_EmailExists() {
//        // Prepare test data
//        User user = new User();
//        user.setUsername("newuser");
//        user.setEmail("existinguser@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
//        when(userRepository.findByUsername(user.getEmail())).thenReturn(user);
//
//        // Execute the register method
//        ResponseEntity<Map<String, String>> response = registrationController.register(user);
//
//        // Verify the response
//        assertNotNull(response);
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//
//        // Verify the save method is not called
//        verify(userRepository, never()).save(user);
//    }
//}
