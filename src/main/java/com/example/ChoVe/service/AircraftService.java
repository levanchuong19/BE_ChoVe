package com.example.ChoVe.service;

import com.example.ChoVe.entity.Aircraft;
import com.example.ChoVe.entity.Airline;
import com.example.ChoVe.model.AircraftRequest;
import com.example.ChoVe.model.AircraftResponse;
import com.example.ChoVe.repository.AircraftRepository;
import com.example.ChoVe.repository.AirlineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AircraftService {

    @Autowired
    AircraftRepository aircraftRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AirlineRepository airlineRepository;

    @PersistenceContext
    EntityManager entityManager;



//    public Aircraft createAircraft (AircraftRequest aircraftRequest) {
//        Aircraft aircraft = modelMapper.map(aircraftRequest, Aircraft.class);
////        Aircraft aircraft = new Aircraft();
//        try {
//            Airline airline = airlineRepository.findAirlineById(aircraftRequest.getAirlineId());
//
//            if(airline == null) throw  new IllegalArgumentException("Airline not found with ID");
//        aircraft.setAirline(airline);
//        aircraft.setName(aircraftRequest.getName());
//        aircraft.setManufacturer(aircraftRequest.getManufacturer());
//        aircraft.setCapacity(aircraftRequest.getCapacity());
//        aircraft.setIRange(aircraftRequest.getIRange());
//        aircraft.setIAtaCode(aircraftRequest.getIAtaCode());
//        aircraft.setICaoCode(aircraftRequest.getICaoCode());
//        aircraft.setDescription(aircraftRequest.getDescription());
//
//            airline.getAircrafts().add(aircraft);
//            airlineRepository.save(airline);
//            aircraftRepository.save(aircraft);
//            return modelMapper.map(aircraft, Aircraft.class);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    public Aircraft createAircraft(AircraftRequest aircraftRequest) {
        Aircraft newaircraft = modelMapper.map(aircraftRequest, Aircraft.class);

        Airline airline = airlineRepository.findAirlineById(aircraftRequest.getAirlineId());
        if (airline == null) throw new EntityNotFoundException("Airline Not Found");

        newaircraft.setAirline(airline.getId());
        try {
           aircraftRepository.saveAndFlush(newaircraft);  // Giải quyết lỗi detached entity
            airline = airlineRepository.findAirlineById(aircraftRequest.getAirlineId());
            if (airline ==null)  throw new EntityNotFoundException("Not al");
//            airline.getAircrafts().add(newaircraft);
            //modelMapper.map(newaircraft, Aircraft.class)
        return newaircraft ;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityNotFoundException("error when saving aircraft to db");
        }
    }




    public List<Aircraft> getAllAircraft(){
        List<Aircraft> aircrafts = aircraftRepository.findAll();
        return aircrafts;
    }

    public Aircraft getAircraft(UUID id){
        Aircraft aircraft = aircraftRepository.findAircraftById(id);
        if (aircraft ==null) throw  new EntityNotFoundException("Aircraft Invalid");
        return aircraft;
    }

//    public AircraftResponse updateAircraft(UUID id, Aircraft aircraft){
//        Aircraft oldAircraft = aircraftRepository.findAircraftById(id);
//        if (oldAircraft == null) throw new EntityNotFoundException("Aircraft Not Found");
//        oldAircraft.setName(aircraft.getName());
//        oldAircraft.setManufacturer(aircraft.getManufacturer());
//        oldAircraft.setCapacity(aircraft.getCapacity());
//        oldAircraft.setIRange(aircraft.getIRange());
//        oldAircraft.setICaoCode(aircraft.getICaoCode());
//        oldAircraft.setIAtaCode(aircraft.getIAtaCode());
//        oldAircraft.setDescription(aircraft.getDescription());
//
//        Aircraft newAircraft = aircraftRepository.save(oldAircraft);
//        return new AircraftResponse(
//                newAircraft.getName(),
//                newAircraft.getManufacturer(),
//                newAircraft.getCapacity(),
//                newAircraft.getIRange(),
//                newAircraft.getICaoCode(),
//                newAircraft.getIAtaCode(),
//                newAircraft.getDescription());
//    }

    public AircraftResponse updateAircraft(UUID id, AircraftRequest aircraftRequest) {
        Aircraft oldAircraft = aircraftRepository.findAircraftById(id);
        if (oldAircraft == null) {
            throw new EntityNotFoundException("Aircraft not found with ID: " + id);
        }

        modelMapper.map(aircraftRequest, oldAircraft);
        Aircraft updatedAircraft = aircraftRepository.save(oldAircraft);

        return modelMapper.map(updatedAircraft, AircraftResponse.class);
    }


    public Aircraft deleteAircraft (UUID id){
        Aircraft aircraft = aircraftRepository.findAircraftById(id);
        if (aircraft == null) throw  new EntityNotFoundException("Aircraft Not Found");
        aircraft.setDeleted(true);
        return aircraftRepository.save(aircraft);
    }
}
