package com.example.ChoVe.repository;

import com.example.ChoVe.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline,String> {
    Airline findAirlineByiCaoCode(String iCaoCode);
    Airline findAirlineById(UUID id);

}
