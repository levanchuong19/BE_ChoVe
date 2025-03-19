package com.example.ChoVe.model;

import com.example.ChoVe.entity.Aircraft;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class AircraftRequest {
    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name;

    @NotBlank(message = "Manufacturer cannot be blank!")
    @Size(max = 100, message = "Manufacturer must not exceed 100 characters")
    String manufacturer;

    @NotNull(message = "Capacity cannot be null!")
    Integer capacity;

    @NotNull(message = "Range cannot be null!")
    Integer iRange;

    @NotBlank(message = "ICAO Code cannot be blank!")
    @Size(min = 4, max = 4, message = "ICAO Code must be exactly 4 characters")
    String iCaoCode;

    @NotBlank(message = "IATA Code cannot be blank!")
    @Size(min = 3, max = 3, message = "IATA Code must be exactly 3 characters")
    String iAtaCode;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;

    @NotNull(message = "airlineId cannot be blank!")
    UUID airlineId;
}
