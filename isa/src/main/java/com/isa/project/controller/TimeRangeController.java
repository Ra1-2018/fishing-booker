package com.isa.project.controller;

import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.TimeRangeDTO;
import com.isa.project.model.Cottage;
import com.isa.project.model.TimeRange;
import com.isa.project.service.CottageService;
import com.isa.project.service.ServiceService;
import com.isa.project.service.TimeRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/timeRanges")
public class TimeRangeController {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private TimeRangeService timeRangeService;

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> createFreePeriod(@RequestBody TimeRangeDTO timeRangeDTO) {

        Cottage cottage = cottageService.findById(timeRangeDTO.getServiceId());

        if(cottage == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        TimeRange freePeriod = new TimeRange(timeRangeDTO.getId(), timeRangeDTO.getStartDate(), timeRangeDTO.getEndDate(), cottage);
        if(serviceService.isFreePeriodValid(freePeriod)) {
            serviceService.addFreePeriod(freePeriod);
            return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.BAD_REQUEST);
        }
    }
}
