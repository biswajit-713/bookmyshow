package com.scaler.lld.bookmyshow.movie;

import lombok.Data;
import lombok.Getter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "movie")
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "movie", sequenceName = "movie_seq", initialValue = 1)
    private Long id;

    private String title;
    @Enumerated(value = EnumType.ORDINAL)
    private Genre genre;
    @Column(name = "RUN_TIME")
    private int durationInMinute;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Actor> actors;
}
