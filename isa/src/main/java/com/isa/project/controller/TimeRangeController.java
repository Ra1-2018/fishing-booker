package com.isa.project.controller;

import com.isa.project.dto.*;
import com.isa.project.model.*;
import com.isa.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/timeRanges")
public class TimeRangeController {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private AppUserService appUserService;

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
        if(serviceService.isPeriodValid(freePeriod)) {
            serviceService.addFreePeriod(freePeriod);
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json", value = "unavailablePeriod/service")
    public ResponseEntity<ServiceDTO> createUnavailablePeriodForService(@RequestBody TimeRangeDTO timeRangeDTO) {

        Service service = serviceService.findById(timeRangeDTO.getServiceId());

        if(service == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        TimeRange unavailablePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), service);
        unavailablePeriod.setAvailable(false);
        if(serviceService.isPeriodValid(unavailablePeriod)) {
            serviceService.addUnavailablePeriod(unavailablePeriod);
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json", value = "unavailablePeriod/owner/{id}")
    public ResponseEntity<ServiceDTO> createUnavailablePeriodForOwnersServices(@RequestBody TimeRangeDTO timeRangeDTO, @PathVariable Long id) {

        AppUser appUser = appUserService.findOne(id);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (appUser.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            CottageOwner cottageOwner = (CottageOwner) appUser;
            Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);

            for (Cottage cottage : cottages) {
                if (cottage == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                TimeRange unavailablePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), cottage);
                unavailablePeriod.setAvailable(false);
                if (serviceService.isPeriodValid(unavailablePeriod)) {
                    serviceService.addUnavailablePeriod(unavailablePeriod);
                    return new ResponseEntity<>(new ServiceDTO(cottage), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ServiceDTO(cottage), HttpStatus.BAD_REQUEST);
                }
            }
        } else if (appUser.getAppUserType() == AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) appUser;
            Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);

            for (Boat boat : boats) {
                if (boat == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                TimeRange unavailablePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), boat);
                unavailablePeriod.setAvailable(false);
                if (serviceService.isPeriodValid(unavailablePeriod)) {
                    serviceService.addUnavailablePeriod(unavailablePeriod);
                    return new ResponseEntity<>(new ServiceDTO(boat), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ServiceDTO(boat), HttpStatus.BAD_REQUEST);
                }
            }
        } else if (appUser.getAppUserType() == AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) appUser;
            Collection<Adventure> adventures = adventureService.findAdventuresByOwner(instructor);

            for (Adventure adventure : adventures) {
                if (adventure == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                TimeRange unavailablePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), adventure);
                unavailablePeriod.setAvailable(false);
                if (serviceService.isPeriodValid(unavailablePeriod)) {
                    serviceService.addUnavailablePeriod(unavailablePeriod);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
            service = serviceService.findById(service.getId());
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ServiceDTO(serviceService.findById(service.getId())), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @GetMapping(value = "unavailablePeriods/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TimeRangeDTO>> findUnavailableByOwner(@PathVariable("id") Long id) {

        AppUser appUser = appUserService.findOne(id);
        if (appUser == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<TimeRangeDTO> rangesDTOS = new ArrayList<>();

        if(appUser.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            CottageOwner cottageOwner = (CottageOwner) appUser;
            Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);

            for (Cottage cottage : cottages) {
                Collection<TimeRange> ranges = timeRangeService.findByService(cottage);
                for (TimeRange range: ranges) {
                    if (range.isAvailable()){
                        ranges.remove(range.getId());
                    }
                }
                for (TimeRange range : ranges) {
                    rangesDTOS.add(new TimeRangeDTO(range));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) appUser;
            Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);

            for (Boat boat : boats) {
                Collection<TimeRange> ranges = timeRangeService.findByService(boat);
                for (TimeRange range: ranges) {
                    if (range.isAvailable()){
                        ranges.remove(range.getId());
                    }
                }
                for (TimeRange range : ranges) {
                    rangesDTOS.add(new TimeRangeDTO(range));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) appUser;
            Collection<Adventure> adventures = adventureService.findAdventuresByOwner(instructor);

            for (Adventure adventure : adventures) {
                Collection<TimeRange> ranges = timeRangeService.findByService(adventure);
                for (TimeRange range: ranges) {
                    if (!range.isAvailable()) {
                        rangesDTOS.add(new TimeRangeDTO(range));
                    }
                }
            }
        }
        return new ResponseEntity<>(rangesDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @GetMapping(value = "unavailablePeriods/service/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TimeRangeDTO>> findUnavailableByService(@PathVariable("id") Long id) {

        Collection<TimeRangeDTO> rangesDTOS = new ArrayList<>();
        Service service = serviceService.findById(id);
        if(service==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (TimeRange range : service.getUnavailablePeriods()) {
            rangesDTOS.add(new TimeRangeDTO(range));
        }
        return new ResponseEntity<>(rangesDTOS, HttpStatus.OK);
    }
}
