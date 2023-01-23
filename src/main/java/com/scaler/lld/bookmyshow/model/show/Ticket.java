package com.scaler.lld.bookmyshow.model.show;

import com.scaler.lld.bookmyshow.model.payment.Payment;
import com.scaler.lld.bookmyshow.model.movie.Movie;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID referenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToMany
    private List<ShowSeat> showSeat;

    @OneToMany(mappedBy = "ticket")
//    ticket owns the relationship
    private List<Payment> payments;
    private double price;

    private LocalDateTime bookingTime;

    @Enumerated(value = EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
