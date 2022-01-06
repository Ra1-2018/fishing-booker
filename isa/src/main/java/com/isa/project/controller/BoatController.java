package com.isa.project.controller;

import com.isa.project.dto.BoatDTO;
import com.isa.project.model.Boat;
import com.isa.project.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/boats")
public class BoatController {
    @Autowired
    private BoatService boatService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BoatDTO>> getBoats() {
        Collection<Boat> boats = boatService.findAll();
        Collection<BoatDTO> boatDTOS = new ArrayList<>();
        for(Boat boat : boats) {
            boatDTOS.add(new BoatDTO(boat));
        }
        return new ResponseEntity<>(boatDTOS, HttpStatus.OK);
    }

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoatDTO> getBoat(@PathVariable("id") Long id) {
        Boat boat = boatService.findById(id);

        if(boat == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new BoatDTO(boat), HttpStatus.OK);
    }
}
