package com.milestone2.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationRequestTest {

    @Test
    public void testGetUsername() {
        AuthenticationRequest request = new AuthenticationRequest("username", "password");
        assertEquals("username", request.getUsername());
    }

    @Test
    public void testSetUsername() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("username");
        assertEquals("username", request.getUsername());
    }

    @Test
    public void testGetPassword() {
        AuthenticationRequest request = new AuthenticationRequest("username", "password");
        assertEquals("password", request.getPassword());
    }

    @Test
    public void testSetPassword() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setPassword("password");
        assertEquals("password", request.getPassword());
    }

    @Test
    public void testConstructor() {
        AuthenticationRequest request = new AuthenticationRequest("username", "password");
        assertEquals("username", request.getUsername());
        assertEquals("password", request.getPassword());
    }

}
