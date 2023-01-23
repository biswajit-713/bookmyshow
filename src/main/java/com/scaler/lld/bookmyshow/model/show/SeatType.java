package com.scaler.lld.bookmyshow.model.show;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "seat_type")
@Table(name = "seat_type")
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public SeatType(String name) {
        this.name = name;
    }
}
