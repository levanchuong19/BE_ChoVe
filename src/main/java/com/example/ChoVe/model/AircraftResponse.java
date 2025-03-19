package com.example.ChoVe.model;

import com.example.ChoVe.entity.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AircraftResponse {
//    UUID id;
    String name;
    String manufacturer;
    Integer capacity;
    Integer iRange;
    String iCaoCode;
    String iAtaCode;
    String description;
    UUID airlineId;


}
