package com.isa.project.model;

import javax.persistence.*;

@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String length;
    @Column
    private String numberOfEngines;
    @Column
    private String enginePower;
    @Column
    private String maximumVelocity;
    @Column
    private String navigationEquipment;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private int capacity;
    @Column
    private String behaviorRules;
    @Column
    private String fishingEquipment;
    @Column
    private String priceList;
    @Column
    private String cancellationTerms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boatOwner_id")
    private BoatOwner boatOwner;

    public Boat() {}

    public Boat(long id, String name, String type, String length, String numberOfEngines, String enginePower, String maximumVelocity, String navigationEquipment, String address, String description, int capacity, String behaviorRules, String fishingEquipment, String priceList, String cancellationTerms) {
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
        this.boatOwner = new BoatOwner();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(String numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getMaximumVelocity() {
        return maximumVelocity;
    }

    public void setMaximumVelocity(String maximumVelocity) {
        this.maximumVelocity = maximumVelocity;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public void setCancellationTerms(String cancellationTerms) {
        this.cancellationTerms = cancellationTerms;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }
}
