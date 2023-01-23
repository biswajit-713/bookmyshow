package com.scaler.lld.bookmyshow.dto.ticket;

import lombok.Getter;

import java.util.List;

@Getter
public class BookTicketRequestDto {
    private final Long movieId;
    private final Long showId;
    private final List<Long> seatIds;

    public BookTicketRequestDto(Long movieId, Long showId, List<Long> seatIds) {
        this.movieId = movieId;
        this.showId = showId;
        this.seatIds = seatIds;
    }
}
