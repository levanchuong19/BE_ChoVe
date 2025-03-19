package com.example.ChoVe.service;

import com.example.ChoVe.entity.Airline;
import com.example.ChoVe.model.AirlineRequest;
import com.example.ChoVe.model.AirlineResponse;
import com.example.ChoVe.repository.AirlineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class AirlineService {
    @Autowired
    AirlineRepository airlineRepository;

    public Airline createAirline(AirlineRequest airlineRequest){
        Airline airline = new Airline();
        airline.setName(airlineRequest.getName());
        airline.setIAtaCode(airlineRequest.getIAtaCode());
        airline.setICaoCode(airlineRequest.getICaoCode());
        airline.setCountry(airlineRequest.getCountry());
        airline.setCallsign(airlineRequest.getCallsign());
        airline.setFounded(airlineRequest.getFounded());
        airline.setDescription(airlineRequest.getDescription());
        try {
            return airlineRepository.save(airline);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public List<Airline> getAllAirline(){
        List<Airline> airlines = airlineRepository.findAll();
        return airlines;
    }

    public Airline getAirline (String icao_code){
        Airline airline = airlineRepository.findAirlineByiCaoCode(icao_code);
        if (airline ==null) throw  new EntityNotFoundException("Airline Invalid");
        return airline;
    }

    public AirlineResponse updateAirline(UUID id, Airline airline){
        Airline oldAirline = airlineRepository.findAirlineById(id);
        if(oldAirline == null) throw  new EntityNotFoundException("Airline Not Found");
        oldAirline.setName(airline.getName());
        oldAirline.setIAtaCode(airline.getIAtaCode());
        oldAirline.setICaoCode(airline.getICaoCode());
        oldAirline.setCountry(airline.getCountry());
        oldAirline.setCallsign(airline.getCallsign());
        oldAirline.setFounded(airline.getFounded());
        oldAirline.setDescription(airline.getDescription());
        if(oldAirline == null) throw  new RuntimeException("Update airline error");
        Airline newAirline = airlineRepository.save(oldAirline);
        return new AirlineResponse(
                newAirline.getName(),
                    newAirline.getICaoCode(),
                    newAirline.getIAtaCode(),
                newAirline.getCountry(),
                newAirline.getCallsign().toLowerCase(Locale.ROOT),
                newAirline.getFounded(),
                newAirline.getDescription()
                );
    }

    public Airline deleteAirline (UUID id){
        Airline airline = airlineRepository.findAirlineById(id);
        if(airline == null) throw  new EntityNotFoundException("Airline Invalid");
        airline.setDeleted(true);
        return airlineRepository.save(airline);
    }
}
