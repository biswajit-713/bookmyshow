package com.scaler.lld.bookmyshow.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "actor")
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "actor", sequenceName = "actor_seq", initialValue = 1)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;

    public Actor(String name) {
        this.name = name;
    }
}


