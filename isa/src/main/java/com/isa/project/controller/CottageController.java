package com.isa.project.controller;

import com.isa.project.dto.CottageDTO;
import com.isa.project.model.Cottage;
import com.isa.project.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/cottages")
public class CottageController {

    @Autowired
    private CottageService cottageService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CottageDTO>> getCottages() {
        Collection<Cottage> cottages = cottageService.findAll();
        Collection<CottageDTO> cottageDTOS = new ArrayList<>();
        for (Cottage cottage : cottages) {
            cottageDTOS.add(new CottageDTO(cottage));
        }
        return new ResponseEntity<>(cottageDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CottageDTO> getCottage(@PathVariable("id") Long id) {
        Cottage cottage = cottageService.findById(id);

        if (cottage == null) {
            return new ResponseEntity<CottageDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }
}
