package com.milestone1.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milestone1.app.models.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
    Rating findByMovieIdAndUserId(Integer movieId, Integer userId);
}
