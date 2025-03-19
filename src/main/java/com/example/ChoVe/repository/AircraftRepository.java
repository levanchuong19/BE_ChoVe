package com.example.ChoVe.repository;

import com.example.ChoVe.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AircraftRepository extends JpaRepository<Aircraft, UUID> {
    Aircraft findAircraftById (UUID id);
    Aircraft findAircraftByiCaoCode (String iCaoCode);
}
