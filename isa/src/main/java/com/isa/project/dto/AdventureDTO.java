package com.isa.project.dto;

import com.isa.project.model.Adventure;
import com.isa.project.model.ReservationCancellation;

public class AdventureDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private String instructorBiography;
    private int maxNumberOfPeople;
    private String behaviorRules;
    private String fishingGear;
    private double pricePerDay;
    private ReservationCancellation cancellation;
    private AppUserDTO instructor;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public String getFishingGear() {
        return fishingGear;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public ReservationCancellation getCancellation() {
        return cancellation;
    }

    public AppUserDTO getInstructor() {
        return instructor;
    }

    public AdventureDTO(Adventure adventure) { this(adventure.getId(), adventure.getName(), adventure.getAddress(), adventure.getDescription(), adventure.getInstructorBiography(), adventure.getMaxNumberOfPeople(), adventure.getBehaviorRules(), adventure.getFishingGear(), adventure.getPricePerDay(), adventure.getCancellation(), new AppUserDTO(adventure.getInstructor()));}

    public AdventureDTO(long id, String name, String address, String description, String instructorBiography, int maxNumberOfPeople, String behaviorRules, String fishingGear, double pricePerDay, ReservationCancellation cancellation, AppUserDTO instructor) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviorRules = behaviorRules;
        this.fishingGear = fishingGear;
        this.pricePerDay = pricePerDay;
        this.cancellation = cancellation;
        this.instructor = instructor;
    }
}
