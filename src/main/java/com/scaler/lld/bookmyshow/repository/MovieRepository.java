package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
