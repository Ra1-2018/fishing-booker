package com.isa.project.controller;

import com.isa.project.dto.*;
import com.isa.project.model.*;
import com.isa.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.*;

@RestController
@RequestMapping("/timeRanges")
public class TimeRangeController {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private AdventureService advantureService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private TimeRangeService timeRangeService;

    @Autowired
    private FreePeriodTransactionService freePeriodTransactionService;

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ServiceDTO> createFreePeriod(@RequestBody TimeRangeDTO timeRangeDTO) {

        Service service = serviceService.findById(timeRangeDTO.getServiceId());

        if(service == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        TimeRange freePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), service);
        if(serviceService.isFreePeriodValid(freePeriod)) {
            serviceService.addFreePeriod(freePeriod);
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json", value = "adventure")
    public ResponseEntity<AdventureDTO> createFreePeriodForAdventure(@RequestBody TimeRangeDTO timeRangeDTO) {

        Adventure adventure = advantureService.findById(timeRangeDTO.getServiceId());

        if(adventure == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        TimeRange freePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), adventure);
        if(serviceService.isFreePeriodValid(freePeriod)) {
            serviceService.addFreePeriod(freePeriod);
            return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<ServiceDTO> deleteFreePeriod(@PathVariable long id) {

        TimeRange freePeriod = timeRangeService.findById(id);
        if(freePeriod == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        Service service = serviceService.findById(freePeriod.getService().getId());
        if(service == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        try {
            freePeriodTransactionService.removeFreePeriod(freePeriod);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
    }
}
