package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowId(Long showId);
}
