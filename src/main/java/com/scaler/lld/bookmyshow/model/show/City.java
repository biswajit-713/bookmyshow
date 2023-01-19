package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "city")
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "city", sequenceName = "city_seq", initialValue = 1)
    private Long id;
    private String name;
}
