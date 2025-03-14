package com.example.ChoVe.repository;

import com.example.ChoVe.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AirportRespository extends JpaRepository<Airport,String> {
    Airport findAirportByiAtaCode(String iAtaCode);
    Airport findAirportByid(UUID id);
    List<Airport> findAirportByNameContainingIgnoreCase(String name);
    @Query("SELECT a FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Airport> searchByName(@Param("name") String name);
}
