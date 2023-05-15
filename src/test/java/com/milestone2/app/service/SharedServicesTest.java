package com.milestone2.app.service;

import com.milestone2.app.repositories.UserRepository;
import com.milestone2.app.service.SharedServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class SharedServicesTest {

    @Test
    void testGetUserRepository() {
        // Create a mock UserRepository
        UserRepository userRepositoryMock = mock(UserRepository.class);

        // Create a SharedServices instance and set the mock UserRepository
        SharedServices sharedServices = new SharedServices();
        sharedServices.setUserRepository(userRepositoryMock);

        // Retrieve the UserRepository from SharedServices
        UserRepository retrievedUserRepository = sharedServices.getUserRepository();

        // Assert that the retrieved UserRepository is the same as the mock
        assertEquals(userRepositoryMock, retrievedUserRepository);
    }

    @Test
    void testGetPasswordEncoder() {
        // Create a mock PasswordEncoder
        PasswordEncoder passwordEncoderMock = mock(PasswordEncoder.class);

        // Create a SharedServices instance and set the mock PasswordEncoder
        SharedServices sharedServices = new SharedServices();
        sharedServices.setPasswordEncoder(passwordEncoderMock);

        // Retrieve the PasswordEncoder from SharedServices
        PasswordEncoder retrievedPasswordEncoder = sharedServices.getPasswordEncoder();

        // Assert that the retrieved PasswordEncoder is the same as the mock
        assertEquals(passwordEncoderMock, retrievedPasswordEncoder);
    }
}
