package com.example.ChoVe.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AirportRequest {
    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name;

    @NotBlank(message = "IATA Code cannot be blank!")
    @Size(min = 3, max = 3, message = "IATA Code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "IATA Code must be uppercase letters only")
    String iAtaCode;

    @NotBlank(message = "ICAO Code cannot be blank!")
    @Size(min = 4, max = 4, message = "ICAO Code must be exactly 4 characters")
    @Pattern(regexp = "^[A-Z]{4}$", message = "ICAO Code must be uppercase letters only")
    String iCaoCode;

    @NotBlank(message = "Location cannot be blank!")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    String location;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;
}
