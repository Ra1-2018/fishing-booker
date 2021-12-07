package com.isa.project.dto;

import com.isa.project.model.Adventure;
import com.isa.project.model.ReservationCancellation;

public class AdventureDTO {
    private long id;
    private String name;
    private String address;

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

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public String getFishingGear() {
        return fishingGear;
    }

    public String getPriceList() {
        return priceList;
    }

    public ReservationCancellation getCancellation() {
        return cancellation;
    }

    private String description;
    private String instructorBiography;
    private int maxPeople;
    private String behaviorRules;
    private String fishingGear;
    private String priceList;
    private ReservationCancellation cancellation;

    public AdventureDTO(Adventure adventure) { this(adventure.getId(), adventure.getName(), adventure.getAddress(), adventure.getDescription(), adventure.getInstructorBiography(), adventure.getMaxPeople(), adventure.getBehaviorRules(), adventure.getFishingGear(), adventure.getPriceList(), adventure.getCancellation());}

    public AdventureDTO(long id, String name, String address, String description, String instructorBiography, int maxPeople, String behaviorRules, String fishingGear, String priceList, ReservationCancellation cancellation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.maxPeople = maxPeople;
        this.behaviorRules = behaviorRules;
        this.fishingGear = fishingGear;
        this.priceList = priceList;
        this.cancellation = cancellation;
    }
}
