package com.isa.project.dto;

import com.isa.project.model.Cottage;

public class CottageDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private int roomsTotalNumber;
    private String behaviorRules;
    private AppUserDTO cottageOwner;
    private double pricePerDay;
    private int maxNumberOfPeople;

    public CottageDTO() { }

    public CottageDTO(Cottage cottage) { this(cottage.getId(), cottage.getName(), cottage.getAddress(), cottage.getDescription(), cottage.getRoomsTotalNumber(), cottage.getBehaviorRules(), new AppUserDTO(cottage.getCottageOwner()), cottage.getPricePerDay(), cottage.getMaxNumberOfPeople()); }

    public CottageDTO(long id, String name, String address, String description, int roomsTotalNumber, String behaviorRules, AppUserDTO cottageOwner, double pricePerDay, int maxNumberOfPeople) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomsTotalNumber = roomsTotalNumber;
        this.behaviorRules = behaviorRules;
        this.cottageOwner = cottageOwner;
        this.pricePerDay = pricePerDay;
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getDescription() { return description; }

    public int getRoomsTotalNumber() { return roomsTotalNumber; }

    public String getBehaviorRules() { return behaviorRules; }

    public AppUserDTO getCottageOwner() { return cottageOwner; }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }
}
