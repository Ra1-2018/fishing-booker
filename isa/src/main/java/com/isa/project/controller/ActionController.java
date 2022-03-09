package com.isa.project.controller;

import com.isa.project.dto.ActionDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationTransactionService reservationTransactionService;

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
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Reservation reservation = reservationTransactionService.makeReservationFromAction(action, client);
            try {
                emailService.sendReservationNotification(reservation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
        } catch(ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
