package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
    SeatType findFirstByName(String type);
}
