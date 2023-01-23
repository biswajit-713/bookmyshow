package com.scaler.lld.bookmyshow.dto.movie;

import com.scaler.lld.bookmyshow.model.movie.Actor;
import com.scaler.lld.bookmyshow.model.movie.Genre;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Value
public class CreateMovieRequest implements Serializable {

    private String title;
    private int runTime;
    private Set<Actor> actors;
    private Genre genre;
}
