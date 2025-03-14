package com.example.ChoVe.api;

import com.example.ChoVe.entity.Airline;
import com.example.ChoVe.model.AirlineRequest;
import com.example.ChoVe.model.AirlineResponse;
import com.example.ChoVe.service.AirlineService;
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
public class AirlineAPI {

    @Autowired
    AirlineService airlineService;

    @PostMapping("airline")
    public ResponseEntity<Airline> createAirline (@Valid @RequestBody AirlineRequest airlineRequest){
        Airline airline = airlineService.createAirline(airlineRequest);
        return  ResponseEntity.ok(airline);
    }

    @GetMapping("airlines")
    public ResponseEntity getAllAirline(){
        return ResponseEntity.ok(airlineService.getAllAirline());
    }

    @GetMapping("airline/{icaoCode}")
    public ResponseEntity getAirline(@PathVariable String icaoCode){
        return ResponseEntity.ok(airlineService.getAirline(icaoCode));
    }

    @PutMapping("airline/{id}")
    public ResponseEntity<AirlineResponse> updateAirline(@PathVariable UUID id, Airline airline){
        AirlineResponse airline1 = airlineService.updateAirline(id, airline);
        return ResponseEntity.ok(airline1);
    }

    @DeleteMapping("airline/{id}")
    public ResponseEntity deleteAirline(@PathVariable UUID id){
        Airline airline = airlineService.deleteAirline(id);
        return ResponseEntity.ok(airline);
    }
}
