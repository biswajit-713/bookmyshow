package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAuditoriumId(Long auditoriumId);
}
