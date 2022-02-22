package com.isa.project.controller;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.*;
import com.isa.project.service.AppUserService;
import com.isa.project.service.ReservationService;
import com.isa.project.service.ServiceService;
import com.isa.project.verification.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private EmailService emailService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByClient(@PathVariable("id") Long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client-cottages/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByClientCottages(@PathVariable("id") Long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getService().getServiceType() == ServiceType.COTTAGE) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client-boats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByClientBoats(@PathVariable("id") Long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getService().getServiceType() == ServiceType.BOAT) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client-adventures/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByClientAdventures(@PathVariable("id") Long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getService().getServiceType() == ServiceType.ADVENTURE) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "client-upcoming/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByClientUpcoming(@PathVariable("id") Long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getReservationStartDateAndTime().after(new Date())) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservationDTO) {
        Service service = serviceService.findById(reservationDTO.getService().getId());
        if(service == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Client client = (Client) appUserService.findOne(reservationDTO.getClient().getId());

        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = new Reservation();
        reservation.setReservationStartDateAndTime(reservationDTO.getReservationStartDateAndTime());
        reservation.setDurationInDays(reservationDTO.getDurationInDays());
        reservation.setNumberOfPeople(reservationDTO.getNumberOfPeople());
        Set<AdditionalService> additionalServices = new HashSet<>();
        for(AdditionalServiceDTO dto : reservationDTO.getAdditionalServices()) {
            AdditionalService additionalService = new AdditionalService(dto.getId(), dto.getName(), dto.getPrice(), service, new HashSet<>(), new HashSet<>());
            reservation.addAdditionalService(additionalService);
        }
        //reservation.setAdditionalServices(additionalServices);
        reservation.setPrice(reservationDTO.getPrice());
        reservation.setClient(client);
        reservation.setService(service);
        reservation.setLocation(reservationDTO.getLocation());
        if(!serviceService.IsReservationValid(reservation)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        serviceService.RemoveFreePeriod(reservation);
        reservationService.save(reservation);
        try {
            emailService.sendReservationNotification(reservation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }
}
