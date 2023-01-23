package com.scaler.lld.bookmyshow.model.show;

import com.scaler.lld.bookmyshow.model.movie.Movie;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity(name = "show")
@Table(name = "SHOW")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Enumerated(value = EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
