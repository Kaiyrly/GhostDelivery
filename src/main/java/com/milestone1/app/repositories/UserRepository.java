package com.milestone1.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milestone1.app.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(int userId);
}
