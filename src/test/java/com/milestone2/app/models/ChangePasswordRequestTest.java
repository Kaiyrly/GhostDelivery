package com.milestone2.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChangePasswordRequestTest {

    @Test
    public void testGetCurrentPassword() {
        ChangePasswordRequest request = new ChangePasswordRequest("oldPassword", "newPassword");
        assertEquals("oldPassword", request.getCurrentPassword());
    }

    @Test
    public void testSetCurrentPassword() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setCurrentPassword("oldPassword");
        assertEquals("oldPassword", request.getCurrentPassword());
    }

    @Test
    public void testGetNewPassword() {
        ChangePasswordRequest request = new ChangePasswordRequest("oldPassword", "newPassword");
        assertEquals("newPassword", request.getNewPassword());
    }

    @Test
    public void testSetNewPassword() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setNewPassword("newPassword");
        assertEquals("newPassword", request.getNewPassword());
    }

    @Test
    public void testConstructor() {
        ChangePasswordRequest request = new ChangePasswordRequest("oldPassword", "newPassword");
        assertEquals("oldPassword", request.getCurrentPassword());
        assertEquals("newPassword", request.getNewPassword());
    }

}
