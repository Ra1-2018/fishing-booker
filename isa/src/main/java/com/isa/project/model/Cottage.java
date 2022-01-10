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

    public Cottage(long id, String name, String description, String behaviorRules, String priceList, String address, int roomsTotalNumber, Set<Reservation> reservations, CottageOwner cottageOwner) {
        super(id, name, description, behaviorRules, priceList, address, ServiceType.COTTAGE, reservations);
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
