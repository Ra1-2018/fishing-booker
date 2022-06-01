package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cottage extends Service{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottageOwner_id")
    private CottageOwner cottageOwner;

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<Room>();

    public Cottage() {
        super();
    }

    public Cottage(long id, String name, String description, String behaviorRules, double pricePerDay, Set<Room> rooms, Set<Reservation> reservations, CottageOwner cottageOwner, Set<TimeRange> freeReservations, int maxNumberOfPeople, Set<AdditionalService> additionalServices, Set<Action> actions, Set<Review> reviews, Set<Client> subscribedClients, Set<Complaint> complaints, Location location) {
        super(id, name, description, behaviorRules, pricePerDay, ServiceType.COTTAGE, reservations, freeReservations, maxNumberOfPeople, additionalServices, actions, reviews, subscribedClients, complaints, location);
        this.rooms = new HashSet<>();
        this.cottageOwner = cottageOwner;
    }

    public CottageOwner getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public Set<Room> getRooms() { return rooms; }

    public void setRooms(Set<Room> rooms) { this.rooms = rooms; }
}
