package com.isa.project.model;

import java.util.ArrayList;

public class Adventure {

    private long id;
    private String name;
    private String address;
    private String description;
    private String instructorBiography;
    private int maxPeople;
    private ArrayList<Reservation> freeReservations;
    private String behaviorRules;
    private String fishingGear;
    private String priceList;
    private ReservationCancellation cancellation;

    public Adventure(long id, String name, String address, String description, String instructorBiography, int maxPeople, ArrayList<Reservation> freeReservations, String behaviorRules, String fishingGear, String priceList, ReservationCancellation cancellation) {
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

    public ArrayList<Reservation> getFreeReservations() {
        return freeReservations;
    }

    public void setFreeReservations(ArrayList<Reservation> freeReservations) {
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
}
