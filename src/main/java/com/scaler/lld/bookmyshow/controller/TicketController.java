package com.scaler.lld.bookmyshow.controller;

import com.scaler.lld.bookmyshow.dto.ticket.BookTicketRequestDto;
import com.scaler.lld.bookmyshow.dto.ticket.BookTicketResponseDto;
import com.scaler.lld.bookmyshow.exception.SeatUnavailableException;
import com.scaler.lld.bookmyshow.model.show.Ticket;
import com.scaler.lld.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BookTicketResponseDto book(@RequestBody BookTicketRequestDto bookTicketRequestDto) {
        try {
            Ticket bookedTicket = ticketService.book(bookTicketRequestDto.getMovieId(), bookTicketRequestDto.getShowId(), bookTicketRequestDto.getSeatIds());
            return new BookTicketResponseDto(bookedTicket.getReferenceId(), bookedTicket.getBookingTime(), bookedTicket.getTicketStatus());
        } catch (SeatUnavailableException e) {
            return null;
        }
    }
}
