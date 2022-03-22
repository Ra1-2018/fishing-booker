package com.isa.project.controller;

import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.TimeRangeDTO;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.ServiceType;
import com.isa.project.model.TimeRange;
import com.isa.project.service.CottageOwnerService;
import com.isa.project.service.CottageService;
import com.isa.project.service.TimeRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    @GetMapping(value = "owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CottageDTO>> getOwnerCottages(@PathVariable("id") Long id) {
        CottageOwner cottageOwner = cottageOwnerService.findById(id);
        Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);
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
    @PostMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<CottageDTO> createCottage(@PathVariable("id") Long id, @RequestBody CottageDTO cottageDTO) {

        CottageOwner cottageOwner = cottageOwnerService.findById(id);

        if (cottageOwner == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cottage cottage = new Cottage();
        cottage.setAddress(cottageDTO.getAddress());
        cottage.setBehaviorRules(cottageDTO.getBehaviorRules());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setName(cottageDTO.getName());
        cottage.setCottageOwner(cottageOwner);
        cottage.setPricePerDay(cottageDTO.getPricePerDay());
        cottage.setMaxNumberOfPeople(cottageDTO.getMaxNumberOfPeople());
        cottage.setRoomsTotalNumber(cottageDTO.getRoomsTotalNumber());
        cottage.setServiceType(ServiceType.COTTAGE);
        cottageService.save(cottage);

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO) {

        Cottage cottage = cottageService.findById(cottageDTO.getId());

        if(cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cottage.setAddress(cottageDTO.getAddress());
        cottage.setBehaviorRules(cottageDTO.getBehaviorRules());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setName(cottageDTO.getName());
        cottage.setPricePerDay(cottageDTO.getPricePerDay());
        cottage.setMaxNumberOfPeople(cottageDTO.getMaxNumberOfPeople());
        cottage.setCottageOwner(cottageOwnerService.findById(cottageDTO.getCottageOwner().getId()));
        cottage.setRoomsTotalNumber(cottageDTO.getRoomsTotalNumber());

        cottageService.save(cottage);

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/delete/{id}")
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
