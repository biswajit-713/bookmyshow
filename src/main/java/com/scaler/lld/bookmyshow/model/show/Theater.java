package com.scaler.lld.bookmyshow.model.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToMany(mappedBy = "theater")
    private Set<Auditorium> auditoriums;

}
