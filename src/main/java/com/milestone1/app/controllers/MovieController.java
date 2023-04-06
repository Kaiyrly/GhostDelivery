package com.milestone1.app.controllers;


import com.milestone1.app.models.Movie;
import com.milestone1.app.repositories.MovieRepository;

import com.milestone1.app.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieByMovieId(@PathVariable int movieId) {
        Movie movie = movieRepository.findByMovieId(movieId);
        if(movie == null) {
            throw new CustomException("Invalid movieId. Such movie does not exist.");
        }
        return movie;
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer movieId, @RequestBody Movie movieUpdates) {
        Movie movie = movieRepository.findByMovieId(movieId);

        if(movie == null) {
            throw new CustomException("Invalid movieId. Such movie does not exist.");
        }

        movie.setTitle(movieUpdates.getTitle());
        movie.setGenres(movieUpdates.getGenres());
        movie.setAverageRating(movieUpdates.getAverageRating());

        Movie updatedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(updatedMovie);
    }

}