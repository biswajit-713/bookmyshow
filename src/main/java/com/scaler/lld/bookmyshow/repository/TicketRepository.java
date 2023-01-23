package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
