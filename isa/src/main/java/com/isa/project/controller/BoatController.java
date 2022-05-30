package com.isa.project.controller;

import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.model.*;
import com.isa.project.service.BoatOwnerService;
import com.isa.project.service.BoatService;
import com.isa.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/boats")
public class BoatController {

    @Autowired
    private BoatService boatService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private LocationService locationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BoatDTO>> getBoats() {
        Collection<Boat> boats = boatService.findAll();
        Collection<BoatDTO> boatDTOS = new ArrayList<>();
        for(Boat boat : boats) {
            boatDTOS.add(new BoatDTO(boat));
        }
        return new ResponseEntity<>(boatDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoatDTO> getBoat(@PathVariable("id") Long id) {
        Boat boat = boatService.findById(id);

        if(boat == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new BoatDTO(boat), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BoatDTO>> getOwnerBoats(@PathVariable("id") Long id) {
        BoatOwner boatOwner = boatOwnerService.findById(id);
        Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);
        Collection<BoatDTO> boatDTOS = new ArrayList<>();
        for (Boat boat : boats) {
            boatDTOS.add(new BoatDTO(boat));
        }
        return new ResponseEntity<>(boatDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<BoatDTO> createBoat(@PathVariable("id") Long id, @RequestBody BoatDTO boatDTO) {

        BoatOwner boatOwner = boatOwnerService.findById(id);

        if (boatOwner == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Boat boat = new Boat();
        boat.setBehaviorRules(boatDTO.getBehaviorRules());
        boat.setBoatOwner(boatOwner);
        boat.setName(boatDTO.getName());
        boat.setDescription(boatDTO.getDescription());
        boat.setMaxNumberOfPeople(boatDTO.getMaxNumberOfPeople());
        boat.setEnginePower(boatDTO.getEnginePower());
        boat.setCancellationTerms(boatDTO.getCancellationTerms());
        boat.setFishingEquipment(boatDTO.getFishingEquipment());
        boat.setLength(boatDTO.getLength());
        boat.setNavigationEquipment(boatDTO.getNavigationEquipment());
        boat.setNumberOfEngines(boatDTO.getNumberOfEngines());
        boat.setType(boatDTO.getType());
        boat.setMaximumVelocity(boatDTO.getMaximumVelocity());
        boat.setPricePerDay(boatDTO.getPricePerDay());
        boat.setServiceType(ServiceType.BOAT);
        boat = boatService.save(boat);

        Location location = new Location( null , boatDTO.getCity(), boatDTO.getStreet(), boatDTO.getNumber(), boatDTO.getZipCode(), boatDTO.getLatitude(), boatDTO.getLongitude(), boat);
        boat.setLocation(location);
        locationService.save(location);

        return new ResponseEntity<>(new BoatDTO(boat), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<BoatDTO> updateBoat(@RequestBody BoatDTO boatDTO) {

        Boat boat = boatService.findById(boatDTO.getId());

        if(boat == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boat.setBehaviorRules(boatDTO.getBehaviorRules());
        boat.setName(boatDTO.getName());
        boat.setDescription(boatDTO.getDescription());
        boat.setMaxNumberOfPeople(boatDTO.getMaxNumberOfPeople());
        boat.setEnginePower(boatDTO.getEnginePower());
        boat.setCancellationTerms(boatDTO.getCancellationTerms());
        boat.setFishingEquipment(boatDTO.getFishingEquipment());
        boat.setLength(boatDTO.getLength());
        boat.setNavigationEquipment(boatDTO.getNavigationEquipment());
        boat.setNumberOfEngines(boatDTO.getNumberOfEngines());
        boat.setType(boatDTO.getType());
        boat.setMaximumVelocity(boatDTO.getMaximumVelocity());
        boat.setPricePerDay(boatDTO.getPricePerDay());

        Location location = new Location( null , boatDTO.getCity(), boatDTO.getStreet(), boatDTO.getNumber(), boatDTO.getZipCode(), boatDTO.getLatitude(), boatDTO.getLongitude(), boat);
        boat.setLocation(location);
        locationService.save(location);
        boatService.save(boat);

        return new ResponseEntity<>(new BoatDTO(boat), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteBoat(@PathVariable long id) {

        Boat boat = boatService.findById(id);

        if (boat != null) {
            boatService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
