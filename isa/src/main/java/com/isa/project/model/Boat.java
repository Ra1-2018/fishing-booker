package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Boat extends Service{
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
    private String fishingEquipment;
    @Column
    private String cancellationTerms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boatOwner_id")
    private BoatOwner boatOwner;

    public Boat() {
        super();
    }

    public Boat(long id, String name, String description, String behaviorRules, double pricePerDay, String address, String type, String length, String numberOfEngines, String enginePower, String maximumVelocity, String navigationEquipment, String fishingEquipment, String cancellationTerms, Set<Reservation> reservations, BoatOwner boatOwner, Set<TimeRange> freeReservations, int maxNumberOfPeople, Set<AdditionalService> additionalServices, Set<Action> actions) {
        super(id, name, description, behaviorRules, pricePerDay, address, ServiceType.BOAT, reservations, freeReservations, maxNumberOfPeople, additionalServices, actions);
        this.type = type;
        this.length = length;
        this.numberOfEngines = numberOfEngines;
        this.enginePower = enginePower;
        this.maximumVelocity = maximumVelocity;
        this.navigationEquipment = navigationEquipment;
        this.fishingEquipment = fishingEquipment;
        this.cancellationTerms = cancellationTerms;
        this.boatOwner = boatOwner;
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

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
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
