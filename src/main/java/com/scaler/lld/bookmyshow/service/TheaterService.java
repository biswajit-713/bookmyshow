package com.scaler.lld.bookmyshow.service;

import com.scaler.lld.bookmyshow.model.show.City;
import com.scaler.lld.bookmyshow.model.show.Theater;
import com.scaler.lld.bookmyshow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater create(String name, String city) {
        Theater theater = new Theater();
        theater.setName(name);
        theater.setCity(new City(city));
        return theaterRepository.save(theater);
    }
}
