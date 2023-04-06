package com.milestone1.app.controllers;

import com.milestone1.app.models.Rating;
import com.milestone1.app.repositories.RatingRepository;
import com.milestone1.app.exception.CustomException;
import com.milestone1.app.models.Movie;
import com.milestone1.app.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieService movieService;


    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/{minRating}")
    public ResponseEntity<List<Movie>> getMoviesWithMinRating(@PathVariable(required = false) Integer minRating) {

        List<Movie> movies = null;
        if (minRating != null && minRating > 0 && minRating < 6) {
            movies = movieService.getMoviesWithMinRating(minRating);
        } else {
            throw new CustomException("Invalid rating value. Rating must be between 1 and 5.");
        }
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating newRating) {
        ratingRepository.save(newRating);
        return ResponseEntity.ok(newRating);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating newRating) {
        Rating rating = ratingRepository.findByMovieIdAndUserId(newRating.getMovieId(), newRating.getUserId());
        if(rating == null) {
            throw new CustomException("Invalid rating object. Object does not exist.");
        }
        rating.setUserId(newRating.getUserId());
        rating.setMovieId(newRating.getMovieId());
        rating.setRating(newRating.getRating());
        rating.setTimestamp(newRating.getTimestamp());

        ratingRepository.save(rating);
        return ResponseEntity.ok(newRating);
    }
}
