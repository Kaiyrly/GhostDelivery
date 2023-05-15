package com.milestone2.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationResponseTest {

    @Test
    public void testGetJwt() {
        AuthenticationResponse response = new AuthenticationResponse("jwtToken");
        assertEquals("jwtToken", response.getJwt());
    }

    @Test
    public void testSetJwt() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setJwt("jwtToken");
        assertEquals("jwtToken", response.getJwt());
    }

    @Test
    public void testConstructor() {
        AuthenticationResponse response = new AuthenticationResponse("jwtToken");
        assertEquals("jwtToken", response.getJwt());
    }

}
