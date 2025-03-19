package com.example.ChoVe.repository;

import com.example.ChoVe.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline,UUID> {
    Airline findAirlineByiCaoCode(String iCaoCode);
    Airline findAirlineById(UUID id);

//    @Query("SELECT a FROM Airline a JOIN FETCH a.aircrafts WHERE a.id = :id")
//    Optional<Airline> findByIdWithAircrafts(@Param("id") UUID id);

//    @Query("SELECT a FROM Airline a JOIN FETCH a.aircrafts WHERE a.id = :id")
//    Airline findAirlineByIdWithAircrafts(@Param("id") UUID id);
}
