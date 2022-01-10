package com.isa.project.controller;

import com.isa.project.dto.BoatReservationDTO;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.service.AppUserService;
import com.isa.project.service.BoatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/boatReservations")
public class BoatReservationController {

    @Autowired
    private BoatReservationService reservationService;

    @Autowired
    private AppUserService userService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BoatReservationDTO>> findByClient(@PathVariable("id") Long id) {
        Client client = (Client) userService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<BoatReservation> reservations = reservationService.findByClient(client);
        Collection<BoatReservationDTO> reservationDTOS = new ArrayList<>();
        for(BoatReservation reservation : reservations) {
            reservationDTOS.add(new BoatReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }
}
