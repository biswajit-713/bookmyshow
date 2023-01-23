package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
