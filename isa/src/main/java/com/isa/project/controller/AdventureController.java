package com.isa.project.controller;

import com.isa.project.dto.AdventureDTO;
import com.isa.project.model.Adventure;
import com.isa.project.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/adventure")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

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
}
