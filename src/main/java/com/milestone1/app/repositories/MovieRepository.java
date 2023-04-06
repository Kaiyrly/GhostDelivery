package com.milestone1.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.milestone1.app.models.Movie;

import java.util.List;


public interface MovieRepository extends MongoRepository<Movie, String> {
    @Query("{ 'averageRating': { $gte: ?0 } }")
    List<Movie> findByRating(double minRating);

    Movie findByMovieId(int movieId);

}
