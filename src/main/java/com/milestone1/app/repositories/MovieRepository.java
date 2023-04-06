package com.milestone1.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.milestone1.app.models.Movie;

import java.util.List;


public interface MovieRepository extends MongoRepository<Movie, String> {
    @Query("{ 'averageRating': { $gte: ?0 } }")
    List<Movie> findByRating(double minRating);

    Movie findByMovieId(Integer movieId);

    @Query(value = "{}", sort = "{movieId : -1}")
    List<Movie> findMaxMovieId();

}
