package com.example.ChoVe.api;

import com.example.ChoVe.entity.Airport;
import com.example.ChoVe.model.AirportRequest;
import com.example.ChoVe.model.AirportResponse;
import com.example.ChoVe.service.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@SecurityRequirement(name="api")
public class AirportAPI {

    @Autowired
    AirportService airportService;

    @GetMapping("airports")
    public ResponseEntity getAllAirport(){
        return ResponseEntity.ok(airportService.getAllAirport());
    }

    @GetMapping("airports/{iAtaCode}")
    public ResponseEntity getAirport (@PathVariable String iAtaCode){
        return ResponseEntity.ok(airportService.getAirport(iAtaCode));
    }

    @PostMapping("airports")
    public ResponseEntity<Airport> createAirport(@Valid @RequestBody AirportRequest airportRequest){
        Airport airport = airportService.createAirport(airportRequest);
        return ResponseEntity.ok(airport);
    }

    @PutMapping("airports/{id}")
    public ResponseEntity<AirportResponse> updateAirport (@PathVariable UUID id, @RequestBody Airport airport){
        AirportResponse airportResponse = airportService.updateAirport(id, airport);
        return  ResponseEntity.ok(airportResponse);
    }

    @DeleteMapping("airports/{id}")
    public ResponseEntity deleteAirport (UUID id){
        Airport airport = airportService.deleteAirport(id);
        return ResponseEntity.ok(airport);
    }
}
