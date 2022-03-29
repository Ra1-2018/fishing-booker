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
        }
        return clients;
    }
}
