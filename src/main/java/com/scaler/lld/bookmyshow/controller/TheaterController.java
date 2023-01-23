package com.scaler.lld.bookmyshow.controller;

import com.scaler.lld.bookmyshow.dto.show.CreateTheaterRequest;
import com.scaler.lld.bookmyshow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }


    @PostMapping("/theaters")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTheater(@RequestBody CreateTheaterRequest createTheaterRequest) {
        theaterService.create(createTheaterRequest.getName(), createTheaterRequest.getCity());
    }
}
