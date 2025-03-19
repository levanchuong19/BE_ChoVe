package com.example.ChoVe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "aircraft")
public class Aircraft {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     UUID id;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    boolean isDeleted = false;

//    @JsonIgnore
//    @ManyToOne
    //mỗi máy bay chỉ thuộc về 1 hãng bay
    @Column(name = "airline_id", nullable = false)
    UUID  airline;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "airline_id", nullable = false)
////    @JsonIgnore
//    Airline airline;





}
