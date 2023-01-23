package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int row;
    private int col;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;
    public Seat(String name, int row, int col, SeatType seatType, Auditorium auditorium) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.auditorium = auditorium;
    }
}
