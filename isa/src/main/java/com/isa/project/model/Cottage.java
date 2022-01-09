package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private int roomsTotalNumber;
    @Column
    private String behaviorRules;

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CottageReservation> freeReservations = new HashSet<CottageReservation>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottageOwner_id")
    private CottageOwner cottageOwner;

    public Cottage() {
    }

    public Cottage(long id, String name, String address, String description, int roomsTotalNumber, String behaviorRules, Set<CottageReservation> freeReservations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomsTotalNumber = roomsTotalNumber;
        this.behaviorRules = behaviorRules;
        this.freeReservations = freeReservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomsTotalNumber() {
        return roomsTotalNumber;
    }

    public void setRoomsTotalNumber(int roomsTotalNumber) {
        this.roomsTotalNumber = roomsTotalNumber;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }

    public CottageOwner getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public Set<CottageReservation> getFreeReservations() { return freeReservations;}

    public void setFreeReservations(Set<CottageReservation> freeReservations) { this.freeReservations = freeReservations; }
}
