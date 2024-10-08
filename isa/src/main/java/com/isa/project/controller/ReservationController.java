package com.isa.project.controller;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.*;
import com.isa.project.service.*;
import com.isa.project.verification.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

    @Autowired
    private CottageService cottageService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private ReservationTransactionService reservationTransactionService;

    @Autowired
    private PenaltyService penaltyService;

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> findOne(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.findById(id);

        if(reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @PostMapping(value="penal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> givePenal(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.findById(reservationDTO.getId());

        if(reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Penalty penalty = new Penalty();
        penalty.setDateIssued(new Date());
        Client client = (Client) appUserService.findOne(reservationDTO.getClient().getId());
        penalty.setClient(client);

        Service service = serviceService.findById(reservationDTO.getId());
        if(service.getServiceType() == ServiceType.BOAT) {
            Boat boat = boatService.findById(service.getId());
            BoatOwner boatOwner = (BoatOwner) appUserService.findOne(boat.getBoatOwner().getId());
            penalty.setBoatOwner(boatOwner);
        } else if(service.getServiceType() == ServiceType.COTTAGE) {
            Cottage cottage = cottageService.findById(service.getId());
            CottageOwner cottageOwner = (CottageOwner) appUserService.findOne(cottage.getCottageOwner().getId());
            penalty.setCottageOwner(cottageOwner);
        } else if(service.getServiceType() == ServiceType.ADVENTURE) {
            Adventure adventure = adventureService.findById(service.getId());
            Instructor instructor = (Instructor) appUserService.findOne(adventure.getInstructor().getId());
            penalty.setInstructor(instructor);
        }

        penaltyService.save(penalty);
        reservation.setReported(true);
        reservationService.save(reservation);

        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @GetMapping(value = "owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> findByOwner(@PathVariable("id") Long id) {

        AppUser appUser = appUserService.findOne(id);
        if (appUser == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<ReservationDTO> reservationDTOS = new ArrayList<>();

        if(appUser.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            CottageOwner cottageOwner = (CottageOwner) appUser;
            Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);

            for (Cottage cottage : cottages) {
                Collection<Reservation> reservations = reservationService.findByService(cottage);
                for (Reservation reservation : reservations) {
                    reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) appUser;
            Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);

            for (Boat boat : boats) {
                Collection<Reservation> reservations = reservationService.findByService(boat);
                for (Reservation reservation : reservations) {
                    reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) appUser;
            Collection<Adventure> adventures = adventureService.findAdventuresByOwner(instructor);

            for (Adventure adventure : adventures) {
                Collection<Reservation> reservations = reservationService.findByService(adventure);
                for (Reservation reservation : reservations) {
                    reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

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

    @PreAuthorize("hasRole('CLIENT') || hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservationDTO) {

        Service service = serviceService.findById(reservationDTO.getService().getId());
        if(service == null) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }

        Client client = (Client) appUserService.findOne(reservationDTO.getClient().getId());
        client.setPoints(client.getPoints()+10);

        if(client == null || client.isSanctioned()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = new Reservation();
        reservation.setReservationStartDateAndTime(reservationDTO.getReservationStartDateAndTime());
        reservation.setDurationInDays(reservationDTO.getDurationInDays());
        reservation.setNumberOfPeople(reservationDTO.getNumberOfPeople());
        Set<AdditionalService> additionalServices = new HashSet<>();
        for(AdditionalServiceDTO dto : reservationDTO.getAdditionalServices()) {
            AdditionalService additionalService = additionalServiceService.findById(dto.getId());
            reservation.addAdditionalService(additionalService);
        }
        reservation.setPrice(reservationDTO.getPrice());
        reservation.setClient(client);
        reservation.setService(service);
        if(!serviceService.IsReservationValid(reservation)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            reservation = reservationTransactionService.makeRegularReservation(reservation);
        }catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            emailService.sendReservationNotification(reservation);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable long id) {
        Reservation reservation = reservationService.findById(id);
        if(reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date reservationStartTime = reservation.getReservationStartDateAndTime();
        Calendar c = Calendar.getInstance();
        c.setTime(reservationStartTime);
        c.add(Calendar.DATE, -3);
        Date threeDaysBeforeReservation = c.getTime();
        /*if(new Date().after(threeDaysBeforeReservation)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        try {
            reservationTransactionService.cancelReservation(reservation);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
