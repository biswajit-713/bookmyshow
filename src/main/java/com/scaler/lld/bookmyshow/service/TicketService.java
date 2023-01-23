package com.scaler.lld.bookmyshow.service;

import com.scaler.lld.bookmyshow.exception.SeatUnavailableException;
import com.scaler.lld.bookmyshow.model.movie.Movie;
import com.scaler.lld.bookmyshow.model.show.Price;
import com.scaler.lld.bookmyshow.model.show.Show;
import com.scaler.lld.bookmyshow.model.show.Ticket;
import com.scaler.lld.bookmyshow.model.show.TicketStatus;
import com.scaler.lld.bookmyshow.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final ShowService showService;
    private final MovieService movieService;

    private final TicketRepository ticketRepository;

    public TicketService(ShowService showService, MovieService movieService, TicketRepository ticketRepository) {
        this.showService = showService;
        this.movieService = movieService;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Ticket book(Long movieId, Long showId, List<Long> seatIds) throws SeatUnavailableException {

        showService.blockSeats(showId, seatIds);

//        get the price of the seats from showseattype
        Price ticketPrice = showService.totalPriceFor(showId, seatIds);

//        create the ticket object
        Ticket ticket = new Ticket();
        Movie movie = movieService.movieWith(movieId);
        ticket.setMovie(movie);
        ticket.setReferenceId(UUID.randomUUID());
        Show show = showService.showWith(showId);
        ticket.setShow(show);
//        save the ticket object with status as pending
        ticket.setTicketStatus(TicketStatus.IN_PROGESS);
        ticket.setPrice(ticketPrice.getValue());
        ticket.setBookingTime(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }
}
