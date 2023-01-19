package com.scaler.lld.bookmyshow.movie;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "actor")
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "actor", sequenceName = "actor_seq", initialValue = 1)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;
}
