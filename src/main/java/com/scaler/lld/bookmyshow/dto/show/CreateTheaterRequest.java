package com.scaler.lld.bookmyshow.dto.show;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTheaterRequest {
    private String name;
    private String city;
}
