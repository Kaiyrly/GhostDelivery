package com.milestone1.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



import com.milestone1.app.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(int userId);

    @Query(value = "{}", sort = "{userId : -1}")
    List<User> findMaxUserId();
}
