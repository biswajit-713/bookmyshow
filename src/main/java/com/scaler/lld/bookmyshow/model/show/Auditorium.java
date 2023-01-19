package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "auditorium")
@Table(name = "auditorium")
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ElementCollection
    @Enumerated(value = EnumType.ORDINAL)
    private Set<Feature> features;
}
