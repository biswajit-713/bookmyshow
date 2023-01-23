package com.scaler.lld.bookmyshow.dto.ticket;

import com.scaler.lld.bookmyshow.model.show.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookTicketResponseDto {
    private final UUID ticketReference;
    private final LocalDateTime bookingTime;
    private final TicketStatus ticketStatus;

    public BookTicketResponseDto(UUID ticketReference, LocalDateTime bookingTime, TicketStatus ticketStatus) {
        this.ticketReference = ticketReference;
        this.bookingTime = bookingTime;
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "BookTicketResponseDto{" +
                "ticketReference=" + ticketReference +
                ", bookingTime=" + bookingTime +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
