package com.isa.project.service;

import com.isa.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OwnerService {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private AdventureService adventureService;

    public Collection<Client> getOwnerClients(AppUser owner) {
        Collection<Client> clients = new ArrayList<>();

        if(owner.getAppUserType() == AppUserType.COTTAGE_OWNER) {
           CottageOwner cottageOwner = (CottageOwner) owner;
            Collection<Cottage> cottages = cottageService.findCottagesByOwner(cottageOwner);

            for (Cottage cottage : cottages) {
                Collection<Reservation> reservations = reservationService.findByService(cottage);
                for(Reservation reservation : reservations) {
                    Client client = reservation.getClient();
                    if(!clients.contains(client)) {
                        clients.add(client);
                    }
                }
            }
        } else if(owner.getAppUserType() == AppUserType.BOAT_OWNER) {
            BoatOwner boatOwner = (BoatOwner) owner;
            Collection<Boat> boats = boatService.findBoatsByOwner(boatOwner);

            for (Boat boat : boats) {
                Collection<Reservation> reservations = reservationService.findByService(boat);
                for(Reservation reservation : reservations) {
                    Client client = reservation.getClient();
                    if(!clients.contains(client)) {
                        clients.add(client);
                    }
                }
            }
        } else if(owner.getAppUserType() == AppUserType.INSTRUCTOR) {
            Instructor instructor = (Instructor) owner;
            Collection<Adventure> adventures = adventureService.findAdventureByInstructor(instructor);

            for (Adventure adventure : adventures) {
                Collection<Reservation> reservations = reservationService.findByService(adventure);
                for(Reservation reservation : reservations) {
                    Client client = reservation.getClient();
                    if(!clients.contains(client)) {
                        clients.add(client);
                    }
                }
            }
        }
        return clients;
    }
}
