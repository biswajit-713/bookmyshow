package com.scaler.lld.bookmyshow.controller;

import com.scaler.lld.bookmyshow.dto.movie.CreateMovieRequest;
import com.scaler.lld.bookmyshow.dto.movie.CreateMovieResponse;
import com.scaler.lld.bookmyshow.model.movie.Movie;
import com.scaler.lld.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<CreateMovieResponse> create(@RequestBody CreateMovieRequest createMovieRequest) {
        Movie movie = movieService.create(createMovieRequest);
        CreateMovieResponse createMovieResponse = new CreateMovieResponse(movie.getTitle(), movie.getDurationInMinute());
        return ResponseEntity.ok(createMovieResponse);
    }
}
