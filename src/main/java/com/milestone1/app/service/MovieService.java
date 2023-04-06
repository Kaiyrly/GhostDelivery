package com.milestone1.app.service;

import com.milestone1.app.models.Movie;
import com.milestone1.app.models.Rating;
import com.milestone1.app.repositories.MovieRepository;
import com.milestone1.app.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByMovieId(Integer movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    public List<Movie> getMoviesWithMinRating(Integer minRating) {
        List<Rating> ratings = ratingRepository.findAll();
        List<Movie> allMovies = movieRepository.findAll();

    
        // Calculate the average rating for each movie
        // (assuming the Rating model has a movieId and rating property)
        allMovies.forEach(movie -> {
                Double avgRating = ratings.stream()
                    .filter(rating -> rating.getMovieId().equals(movie.getMovieId()))
                    .mapToInt(Rating::getRating)
                    .average()
                    .orElse(0.0);
            movie.setAverageRating(avgRating);
        });
    
        // Filter movies with average rating greater than or equal to minRating
        List<Movie> filteredMovies = allMovies.stream()
                .filter(movie -> movie.getAverageRating() >= minRating)
                .collect(Collectors.toList());
    
        return filteredMovies;
        // return movieRepository.findByRating(minRating);
    }

}