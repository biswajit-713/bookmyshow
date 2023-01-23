package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "show_seat_type")
public class ShowSeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;
    @Column(nullable = false)
    private double price;

    public ShowSeatType(Show show, SeatType seatType, double price) {
        this.show = show;
        this.seatType = seatType;
        this.price = price;
    }
}
