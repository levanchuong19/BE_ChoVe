package com.example.ChoVe.api;

import com.example.ChoVe.entity.Aircraft;
import com.example.ChoVe.model.AircraftRequest;
import com.example.ChoVe.model.AircraftResponse;
import com.example.ChoVe.service.AircraftService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@SecurityRequirement(name="api")
public class AircraftAPI {

    @Autowired
    AircraftService aircraftService;
    @PostMapping("aircraft")
    public ResponseEntity<Aircraft> createAircraft (@Valid @RequestBody AircraftRequest aircraftRequest){
        return ResponseEntity.ok(aircraftService.createAircraft(aircraftRequest));
    }

    @GetMapping("aircraft")
    public ResponseEntity getAllAircraft(){
        return ResponseEntity.ok(aircraftService.getAllAircraft());
    }



    @GetMapping("aircraft/{id}")
    public ResponseEntity getAircraft(@PathVariable UUID id){
        Aircraft aircraft = aircraftService.getAircraft(id);
        return ResponseEntity.ok(aircraft);
    }

    @PutMapping("aircraft/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft (@PathVariable UUID id, @RequestBody AircraftRequest aircraftRequest){
        AircraftResponse aircraftResponse = aircraftService.updateAircraft(id, aircraftRequest);
        return  ResponseEntity.ok(aircraftResponse);
    }

    @DeleteMapping("aircraft/{id}")
    public ResponseEntity deleteAircraft(@PathVariable UUID id){
        return ResponseEntity.ok(aircraftService.deleteAircraft(id));
    }
}
