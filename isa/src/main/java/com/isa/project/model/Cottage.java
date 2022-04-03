package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cottage extends Service{

    @Column
    private int roomsTotalNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottageOwner_id")
    private CottageOwner cottageOwner;

    public Cottage() {
        super();
    }

    public Cottage(long id, String name, String description, String behaviorRules, double pricePerDay, String address, int roomsTotalNumber, Set<Reservation> reservations, CottageOwner cottageOwner, Set<TimeRange> freeReservations, int maxNumberOfPeople, Set<AdditionalService> additionalServices, Set<Action> actions, Set<Review> reviews, Set<Client> subscribedClients, Set<Complaint> complaints, String images) {
        super(id, name, description, behaviorRules, pricePerDay, address, ServiceType.COTTAGE, reservations, freeReservations, maxNumberOfPeople, additionalServices, actions, reviews, subscribedClients, complaints, images);
        this.roomsTotalNumber = roomsTotalNumber;
        this.cottageOwner = cottageOwner;
    }

    public int getRoomsTotalNumber() {
        return roomsTotalNumber;
    }

    public void setRoomsTotalNumber(int roomsTotalNumber) {
        this.roomsTotalNumber = roomsTotalNumber;
    }

    public CottageOwner getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }
}
