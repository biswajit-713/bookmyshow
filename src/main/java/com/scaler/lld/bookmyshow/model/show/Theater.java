package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "theater")
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "theatre", sequenceName = "theatre_seq", initialValue = 1)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private Set<Auditorium> auditoriums;
}
