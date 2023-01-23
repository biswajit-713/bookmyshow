package com.scaler.lld.bookmyshow.repository;

import com.scaler.lld.bookmyshow.model.show.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "30000")
    })
    List<ShowSeat> findByShowId(Long showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "30000")
    })
    List<ShowSeat> findByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);
}
