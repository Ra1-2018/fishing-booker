package com.isa.project.dto;

import com.isa.project.model.Boat;

public class BoatDTO {
    private long id;
    private String name;
    private String type;
    private String length;
    private String numberOfEngines;
    private String enginePower;
    private String maximumVelocity;
    private String navigationEquipment;
    private String address;
    private String description;
    private int capacity;
    private String behaviorRules;
    private String fishingEquipment;
    private String priceList;
    private String cancellationTerms;
    private AppUserDTO boatOwner;

    public BoatDTO() {}

    public BoatDTO(Boat boat) { this(boat.getId(), boat.getName(), boat.getType(), boat.getLength(), boat.getNumberOfEngines(), boat.getEnginePower(), boat.getMaximumVelocity(), boat.getNavigationEquipment(), boat.getAddress(), boat.getDescription(), boat.getCapacity(), boat.getBehaviorRules(), boat.getFishingEquipment(), boat.getPriceList(), boat.getCancellationTerms(), new AppUserDTO(boat.getBoatOwner())); }

    public BoatDTO(long id, String name, String type, String length, String numberOfEngines, String enginePower, String maximumVelocity, String navigationEquipment, String address, String description, int capacity, String behaviorRules, String fishingEquipment, String priceList, String cancellationTerms, AppUserDTO boatOwner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.numberOfEngines = numberOfEngines;
        this.enginePower = enginePower;
        this.maximumVelocity = maximumVelocity;
        this.navigationEquipment = navigationEquipment;
        this.address = address;
        this.description = description;
        this.capacity = capacity;
        this.behaviorRules = behaviorRules;
        this.fishingEquipment = fishingEquipment;
        this.priceList = priceList;
        this.cancellationTerms = cancellationTerms;
        this.boatOwner = boatOwner;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLength() {
        return length;
    }

    public String getNumberOfEngines() {
        return numberOfEngines;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public String getMaximumVelocity() {
        return maximumVelocity;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public String getPriceList() {
        return priceList;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public AppUserDTO getBoatOwner() {
        return boatOwner;
    }
}
