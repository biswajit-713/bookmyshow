package com.scaler.lld.bookmyshow.service;

import com.scaler.lld.bookmyshow.dto.movie.CreateMovieRequest;
import com.scaler.lld.bookmyshow.model.movie.Movie;
import com.scaler.lld.bookmyshow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie create(CreateMovieRequest createMovieRequest) {
        Movie movie = new Movie();
        movie.setTitle(createMovieRequest.getTitle());
        movie.setGenre(createMovieRequest.getGenre());
        movie.setDurationInMinute(createMovieRequest.getRunTime());
        movie.setActors(createMovieRequest.getActors());

        return movieRepository.save(movie);
    }

    public Movie movieWith(Long movieId) {
        return movieRepository.findById(movieId).get();
    }
}
