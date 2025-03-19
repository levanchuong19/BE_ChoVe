package com.example.ChoVe.model;

import com.example.ChoVe.entity.Aircraft;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class AirlineRequest {

    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name;

    @NotBlank(message = "IATA Code cannot be blank!")
    @Size(min = 2, max = 2, message = "IATA Code must be exactly 2 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "IATA Code must be uppercase letters only")
    String iAtaCode;

    @NotBlank(message = "ICAO Code cannot be blank!")
    @Size(min = 3, max = 3, message = "ICAO Code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "ICAO Code must be uppercase letters only")
    String iCaoCode;

    @NotBlank(message = "Location cannot be blank!")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    String country;

    @NotBlank(message = "Callsign cannot be blank!")
    @Size(max = 255, message = "Callsign must not exceed 255 characters")
    String callsign;

    @NotBlank(message = "Founded cannot be blank!")
    @Size(max = 4, message = "Founded must not exceed 255 characters")
    String founded;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Set<Aircraft> aircrafts;
}
