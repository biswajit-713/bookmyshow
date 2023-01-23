package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findByShowId(Long showId);
}
