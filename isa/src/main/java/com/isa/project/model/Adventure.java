package com.isa.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adventure {

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
    private String instructorBiography;
    @Column
    private int maxPeople;
    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureReservation> freeReservations = new HashSet<AdventureReservation>();
    @Column
    private String behaviorRules;
    @Column
    private String fishingGear;
    @Column
    private String priceList;
    @Column
    private ReservationCancellation cancellation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Adventure() { }

    public Adventure(long id, String name, String address, String description, String instructorBiography, int maxPeople, Set<AdventureReservation> freeReservations, String behaviorRules, String fishingGear, String priceList, ReservationCancellation cancellation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.maxPeople = maxPeople;
        this.freeReservations = freeReservations;
        this.behaviorRules = behaviorRules;
        this.fishingGear = fishingGear;
        this.priceList = priceList;
        this.cancellation = cancellation;
        this.instructor = new Instructor();
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

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public void setInstructorBiography(String instructorBiography) {
        this.instructorBiography = instructorBiography;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Set<AdventureReservation> getFreeReservations() {
        return freeReservations;
    }

    public void setFreeReservations(Set<AdventureReservation> freeReservations) {
        this.freeReservations = freeReservations;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }

    public String getFishingGear() {
        return fishingGear;
    }

    public void setFishingGear(String fishingGear) {
        this.fishingGear = fishingGear;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public ReservationCancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(ReservationCancellation cancellation) {
        this.cancellation = cancellation;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
