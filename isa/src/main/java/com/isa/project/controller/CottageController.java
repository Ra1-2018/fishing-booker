package com.isa.project.controller;

import com.isa.project.dto.CottageDTO;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.service.CottageOwnerService;
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

    @Autowired
    private CottageOwnerService cottageOwnerService;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> createCottage(@RequestBody CottageDTO cottageDTO) {

        if (cottageDTO.getCottageOwner() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CottageOwner cottageOwner = cottageOwnerService.findOneWithCottages(cottageDTO.getCottageOwner().getId());

        if (cottageOwner == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cottage cottage = new Cottage();
        cottage.setAddress(cottageDTO.getAddress());
        cottage.setBehaviorRules(cottageDTO.getBehaviorRules());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setName(cottageDTO.getName());
        cottage.setCottageOwner(cottageOwner);
        cottageOwner.addCottage(cottage);

        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO) {

        Cottage cottage = cottageService.findById(cottageDTO.getId());

        if(cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cottage.setAddress(cottageDTO.getAddress());
        cottage.setBehaviorRules(cottageDTO.getBehaviorRules());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setName(cottageDTO.getName());
        cottage.setCottageOwner(cottageOwnerService.findOneWithCottages(cottageDTO.getCottageOwner().getId()));

        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCottage(@PathVariable long id) {

        Cottage cottage = cottageService.findById(id);

        if (cottage != null) {
            cottageService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
