package com.scaler.lld.bookmyshow.model.show;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "show_seat")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Enumerated(value = EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    public ShowSeat(Show show, Seat seat, BookingStatus bookingStatus) {
        this.show = show;
        this.seat = seat;
        this.bookingStatus = bookingStatus;
    }
}

