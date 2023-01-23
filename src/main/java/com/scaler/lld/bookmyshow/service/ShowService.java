package com.scaler.lld.bookmyshow.service;

import com.scaler.lld.bookmyshow.exception.SeatUnavailableException;
import com.scaler.lld.bookmyshow.model.show.*;
import com.scaler.lld.bookmyshow.repository.SeatRepository;
import com.scaler.lld.bookmyshow.repository.ShowRepository;
import com.scaler.lld.bookmyshow.repository.ShowSeatRepository;
import com.scaler.lld.bookmyshow.repository.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowSeatRepository showSeatRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public ShowService(ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showSeatTypeRepository, ShowRepository showRepository, SeatRepository seatRepository) {
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    public void blockSeats(Long showId, List<Long> seatIds) throws SeatUnavailableException {
//        todo - the below 2 statements can be combined into one repository query
        List<ShowSeat> showSeats = showSeatRepository.findByShowId(showId);
        List<ShowSeat> requestSeats = showSeats.stream()
                .filter(showSeat -> seatIds.contains(showSeat.getSeat().getId())).toList();

        Optional<ShowSeat> anyUnavailableSeat = requestSeats.stream()
                .filter(showSeat -> showSeat.getBookingStatus() != BookingStatus.AVAILABLE)
                .findAny();

        if (anyUnavailableSeat.isPresent()) {
            throw new SeatUnavailableException("request seat is not available. Please choose another");
        }

        requestSeats.forEach(showSeat -> showSeat.setBookingStatus(BookingStatus.LOCKED));
        showSeatRepository.saveAll(showSeats);
    }

    public Price totalPriceFor(Long showId, List<Long> seatIds) {
        List<ShowSeatType> seatTypes = showSeatTypeRepository.findByShowId(showId);
        List<Seat> requestedSeats = seatRepository.findAllById(seatIds);
        double totalPrice = 0;

        for (Seat seat: requestedSeats) {
            for (ShowSeatType showSeatType: seatTypes) {
                if (seat.getSeatType().equals(showSeatType.getSeatType())) {
                    totalPrice = totalPrice + showSeatType.getPrice();
                }
            }
        }

        return new Price(totalPrice);
    }

    public Show showWith(Long showId) {
        return showRepository.findById(showId).get();
    }
}
