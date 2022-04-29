package com.isa.project.dto;

import com.isa.project.model.Action;
import com.isa.project.model.AdditionalService;
import com.isa.project.model.Boat;
import com.isa.project.model.TimeRange;

import java.util.HashSet;
import java.util.Set;

public class BoatDTO {
    private long id;
    private String name;
    private String type;
    private String length;
    private int numberOfEngines;
    private String enginePower;
    private String maximumVelocity;
    private String navigationEquipment;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String description;
    private int maxNumberOfPeople;
    private String behaviorRules;
    private String fishingEquipment;
    private double pricePerDay;
    private String cancellationTerms;
    private AppUserDTO boatOwner;
    private double averageGrade;
    private Set<AdditionalServiceDTO> additionalServices;
    private Set<TimeRangeDTO> freePeriods;
    private Set<ActionDTO> actions;

    public BoatDTO() {}

    public BoatDTO(Boat boat) {
        this.id = boat.getId();
        this.name = boat.getName();
        this.type = boat.getType();
        this.length = boat.getLength();
        this.numberOfEngines = boat.getNumberOfEngines();
        this.enginePower = boat.getEnginePower();
        this.maximumVelocity = boat.getMaximumVelocity();
        this.navigationEquipment = boat.getNavigationEquipment();
        this.city = boat.getLocation().getCity();
        this.street = boat.getLocation().getStreet();
        this.number = boat.getLocation().getNumber();
        this.zipCode = boat.getLocation().getZipCode();
        this.latitude = boat.getLocation().getLatitude();
        this.longitude = boat.getLocation().getLongitude();
        this.description = boat.getDescription();
        this.maxNumberOfPeople = boat.getMaxNumberOfPeople();
        this.behaviorRules = boat.getBehaviorRules();
        this.fishingEquipment = boat.getFishingEquipment();
        this.pricePerDay = boat.getPricePerDay();
        this.cancellationTerms = boat.getCancellationTerms();
        this.boatOwner = new AppUserDTO(boat.getBoatOwner());
        this.averageGrade = boat.getAverageGrade();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : boat.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.freePeriods = new HashSet<>();
        for(TimeRange timeRange : boat.getFreePeriods()) {
            this.freePeriods.add(new TimeRangeDTO(timeRange));
        }
        this.actions = new HashSet<>();
        for(Action action: boat.getActions()) {
            this.actions.add(new ActionDTO(action));
        }
    }

    public BoatDTO(long id, String name, String type, String length, int numberOfEngines, String enginePower, String maximumVelocity, String navigationEquipment, String city, String street, String number, String zipCode, String latitude, String longitude, String description, int maxNumberOfPeople, String behaviorRules, String fishingEquipment, double pricePerDay, String cancellationTerms, AppUserDTO boatOwner, double averageGrade, Set<AdditionalServiceDTO> additionalServices) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.numberOfEngines = numberOfEngines;
        this.enginePower = enginePower;
        this.maximumVelocity = maximumVelocity;
        this.navigationEquipment = navigationEquipment;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviorRules = behaviorRules;
        this.fishingEquipment = fishingEquipment;
        this.pricePerDay = pricePerDay;
        this.cancellationTerms = cancellationTerms;
        this.boatOwner = boatOwner;
        this.averageGrade = averageGrade;
        this.additionalServices = additionalServices;
        this.freePeriods = freePeriods;
        this.actions = actions;
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

    public int getNumberOfEngines() {
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

    public String getCity() { return city; }

    public String getStreet() { return street; }

    public String getNumber() { return number; }

    public String getZipCode() { return zipCode; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public String getDescription() {
        return description;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public AppUserDTO getBoatOwner() {
        return boatOwner;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public Set<TimeRangeDTO> getFreePeriods() { return freePeriods; }

    public Set<ActionDTO> getActions() { return actions; }
}
