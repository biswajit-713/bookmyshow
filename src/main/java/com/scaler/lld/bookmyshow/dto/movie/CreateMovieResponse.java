package com.scaler.lld.bookmyshow.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CreateMovieResponse implements Serializable {
    private String title;
    private int runTime;
}
