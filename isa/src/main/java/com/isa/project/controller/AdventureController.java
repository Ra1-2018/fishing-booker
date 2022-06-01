package com.isa.project.controller;

import com.isa.project.dto.AdventureDTO;
import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.model.*;
import com.isa.project.service.AdventureService;
import com.isa.project.service.InstructorService;
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
@RequestMapping("/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private LocationService locationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AdventureDTO>> getAdventures() {
        Collection<Adventure> adventures = adventureService.findAll();
        Collection<AdventureDTO> adventureDTOS = new ArrayList<>();
        for (Adventure adventure : adventures) {
            adventureDTOS.add(new AdventureDTO(adventure));
        }
        return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdventureDTO> getAdventure(@PathVariable("id") Long id) {
        Adventure adventure = adventureService.findById(id);

        if(adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AdventureDTO>> getOwnerAdventures(@PathVariable("id") Long id) {
        Instructor instructor = instructorService.findById(id);
        Collection<Adventure> adventures = adventureService.findAdventuresByOwner(instructor);
        Collection<AdventureDTO> adventureDTOS = new ArrayList<>();
        for (Adventure a : adventures) {
            adventureDTOS.add(new AdventureDTO(a));
        }
        return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<AdventureDTO> createAdventure(@PathVariable("id") Long id, @RequestBody AdventureDTO adventureDTO) {

        Instructor instructor = instructorService.findById(id);

        if (instructor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Adventure adventure = new Adventure();
        adventure.setBehaviorRules(adventureDTO.getBehaviorRules());
        adventure.setDescription(adventureDTO.getDescription());
        adventure.setName(adventureDTO.getName());
        adventure.setInstructor(instructor);
        adventure.setPricePerDay(adventureDTO.getPricePerDay());
        adventure.setMaxNumberOfPeople(adventureDTO.getMaxNumberOfPeople());
        adventure.setCancellation(adventureDTO.getCancellation());
        adventure.setFishingGear(adventureDTO.getFishingGear());
        adventure.setInstructorBiography(adventureDTO.getInstructorBiography());
        adventure.setServiceType(ServiceType.ADVENTURE);
        adventureService.save(adventure);

        Location location = new Location( null , adventureDTO.getCity(), adventureDTO.getStreet(), adventureDTO.getNumber(), adventureDTO.getZipCode(), adventureDTO.getLatitude(), adventureDTO.getLongitude(), adventure);
        adventure.setLocation(location);
        locationService.save(location);

        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<AdventureDTO> updateAdventure(@RequestBody AdventureDTO adventureDTO) {

        Adventure adventure = adventureService.findById(adventureDTO.getId());

        if(adventure == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        adventure.setBehaviorRules(adventureDTO.getBehaviorRules());
        adventure.setDescription(adventureDTO.getDescription());
        adventure.setName(adventureDTO.getName());
        adventure.setPricePerDay(adventureDTO.getPricePerDay());
        adventure.setMaxNumberOfPeople(adventureDTO.getMaxNumberOfPeople());
        adventure.setCancellation(adventureDTO.getCancellation());
        adventure.setFishingGear(adventureDTO.getFishingGear());
        adventure.setInstructorBiography(adventureDTO.getInstructorBiography());

        adventureService.save(adventure);

        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAdventure(@PathVariable long id) {

        Adventure adventure = adventureService.findById(id);

        if (adventure != null) {
            adventureService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
