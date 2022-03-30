package com.isa.project.controller;

import com.isa.project.dto.ReportDTO;
import com.isa.project.model.*;
import com.isa.project.service.AppUserService;
import com.isa.project.service.ReportService;
import com.isa.project.service.ReservationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @PostMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDTO> save(@PathVariable("id") Long id, @RequestBody ReportDTO reportDTO) {

        Reservation reservation = reservationService.findById(id);

        if(reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Client client = (Client) appUserService.findOne(reportDTO.getClient().getId());

        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = appUserService.findOne(reportDTO.getOwner().getId());

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Report report = new Report();
        report.setClient(client);
        report.setComment(reportDTO.getComment());
        report.setSanctionRequested(reportDTO.isSanctionRequested());

        if(appUser.getAppUserType()== AppUserType.COTTAGE_OWNER) {
            CottageOwner cottageOwner = (CottageOwner) appUser;
            report.setCottageOwner(cottageOwner);
        }
        else if(appUser.getAppUserType()== AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) appUser;
            report.setBoatOwner(boatOwner);
        }
        else if(appUser.getAppUserType()== AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) appUser;
            report.setInstructor(instructor);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reservation.setReported(true);
        reservationService.save(reservation);
        report = reportService.save(report);

        return new ResponseEntity<>(new ReportDTO(report), HttpStatus.OK);
    }
}
