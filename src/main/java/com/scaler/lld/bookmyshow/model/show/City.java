package com.scaler.lld.bookmyshow.model.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "city")
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "city", sequenceName = "city_seq", initialValue = 1)
    private Long id;
    private String name;

    public City(String city) {
        this.name = city;
    }
}
