package com.example.ChoVe.service;

import com.example.ChoVe.entity.Airport;
import com.example.ChoVe.exception.AccountNotFoundException;
import com.example.ChoVe.model.AirportRequest;
import com.example.ChoVe.model.AirportResponse;
import com.example.ChoVe.repository.AirportRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AirportService {

    @Autowired
    AirportRespository airportRespository;

    public List<Airport> getAllAirport(){
        List<Airport> airports =  airportRespository.findAll();
        return airports;
    }

    public  Airport getAirport(String iAtaCode){
        Airport airport = airportRespository.findAirportByiAtaCode(iAtaCode);
        if(airport == null) throw new AccountNotFoundException("Airport invalid");
        return airport;
    }

    public Airport createAirport(AirportRequest airportRequest){
        Airport airport = new Airport();
        airport.setName(airportRequest.getName());
        airport.setIAtaCode(airportRequest.getIAtaCode());
        airport.setICaoCode(airportRequest.getICaoCode());
        airport.setLocation(airportRequest.getLocation());
        airport.setDescription(airportRequest.getDescription());
        try {
            return airportRespository.save(airport);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public Airport getAirportById(UUID id){
        Airport airport = airportRespository.findAirportByid(id);
        if(airport== null) throw  new AccountNotFoundException("Airport Not Found");
        return airport;
    }

    public AirportResponse updateAirport (UUID id, Airport airport){
           Airport oldAirport = getAirportById(id);
            oldAirport.setName(airport.getName());
            oldAirport.setIAtaCode(airport.getIAtaCode());
            oldAirport.setICaoCode(airport.getICaoCode());
            oldAirport.setLocation(airport.getLocation());
            oldAirport.setDescription(airport.getDescription());
            Airport newAirport = airportRespository.save(oldAirport);
            return new AirportResponse(newAirport.getName(),
                    newAirport.getIAtaCode(),
                    newAirport.getICaoCode(),
                    newAirport.getLocation(),
                    newAirport.getDescription()
                    );
    }

    public Airport deleteAirport(UUID id){
        Airport airport = airportRespository.findAirportByid(id);
        if(airport == null) throw new AccountNotFoundException("Airport Not Found");
         airport.setDeleted(true);
         return airportRespository.save(airport);
    }
}
