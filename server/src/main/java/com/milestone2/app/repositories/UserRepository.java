package com.milestone2.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.milestone2.app.models.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
