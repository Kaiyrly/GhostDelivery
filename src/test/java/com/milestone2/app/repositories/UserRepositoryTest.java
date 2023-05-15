//package com.milestone2.app.repositories;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import com.milestone2.app.models.User;
//
//@DataMongoTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void givenUsername_whenFindUserByUsername_thenReturnUser() {
//        // Arrange
//        User user = new User();
//        user.setUsername("johndoe");
//        user.setPassword("password123");
//        user.setRating(4.5);
//        userRepository.save(user);
//
//        // Act
//        User foundUser = userRepository.findByUsername("johndoe");
//
//        // Assert
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getUsername()).isEqualTo("johndoe");
//        assertThat(foundUser.getPassword()).isEqualTo("password123");
//        assertThat(foundUser.getRating()).isEqualTo(4.5);
//    }
//
//}
