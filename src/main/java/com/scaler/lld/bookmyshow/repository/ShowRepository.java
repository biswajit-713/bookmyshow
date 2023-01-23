package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
