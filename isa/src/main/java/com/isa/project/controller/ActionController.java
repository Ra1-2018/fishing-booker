package com.isa.project.controller;

import com.isa.project.dto.*;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/actions")
public class ActionController {
    @Autowired
    private ActionService actionService;

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationTransactionService reservationTransactionService;

    @Autowired
    private ActionTransactionService actionTransactionService;

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ActionDTO> createAction(@RequestBody ActionDTO actionDTO) {

        Service service = serviceService.findById(actionDTO.getService().getId());

        if(service == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        Action action = new Action();
        action.setStartTime(actionDTO.getStartTime());
        action.setDurationInDays(actionDTO.getDurationInDays());
        action.setPrice(actionDTO.getPrice());
        action.setMaxNumberOfPeople(actionDTO.getMaxNumberOfPeople());
        action.setService(service);

        Collection<AdditionalServiceDTO> additionalServiceDTOS = actionDTO.getAdditionalServices();

        if (additionalServiceDTOS != null ) {
            for(AdditionalServiceDTO additionalServiceDTO : additionalServiceDTOS) {
                action.addAdditionalService(additionalServiceService.findById(additionalServiceDTO.getId()));
            }
        }

        if(!serviceService.IsActionValid(action)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            action = actionTransactionService.createAction(action);
        }catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            emailService.sendActionNotification(action);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return new ResponseEntity<>(new ActionDTO(action), HttpStatus.OK);
    }

    @GetMapping(value = "service/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ActionDTO>> findByService(@PathVariable("id") Long id) {
        Service service = serviceService.findById(id);
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Action> actions = actionService.findByService(service);
        Collection<ActionDTO> actionDTOS = new ArrayList<>();
        for(Action action : actions) {
            actionDTOS.add(new ActionDTO((action)));
        }
        return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "reservation/{clientId}/{actionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> makeReservationFromAction(@PathVariable("clientId") Long clientId, @PathVariable("actionId") Long actionId) {
        Action action = actionService.findById(actionId);
        if(action == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Client client = (Client) appUserService.findOne(clientId);
        if(client == null || client.isSanctioned()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Reservation reservation = reservationTransactionService.makeReservationFromAction(action, client);
            try {
                emailService.sendReservationNotification(reservation);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
        } catch(ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('BOAT_OWNER') || hasRole('INSTRUCTOR')")
    @GetMapping(value = "owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ActionDTO>> findByOwner(@PathVariable("id") Long id) {

        AppUser appUser = appUserService.findOne(id);
        if (appUser == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<ActionDTO> actionDTOS = new ArrayList<>();

        if(appUser.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            CottageOwner cottageOwner = (CottageOwner) appUser;
            Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);

            for (Cottage cottage : cottages) {
                Collection<Action> actions = actionService.findByService(cottage);
                for (Action action : actions) {
                    actionDTOS.add(new ActionDTO(action));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) appUser;
            Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);

            for (Boat boat : boats) {
                Collection<Action> actions = actionService.findByService(boat);
                for (Action action : actions) {
                    actionDTOS.add(new ActionDTO(action));
                }
            }
        }
        else if (appUser.getAppUserType() == AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) appUser;
            Collection<Adventure> adventures = adventureService.findAdventuresByOwner(instructor);

            for (Adventure adventure : adventures) {
                Collection<Action> actions = actionService.findByService(adventure);
                for (Action action : actions) {
                    actionDTOS.add(new ActionDTO(action));
                }
            }
        }
        return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
    }
}
