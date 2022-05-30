package com.isa.project.dto;

import com.isa.project.model.*;

import java.util.HashSet;
import java.util.Set;

public class AdventureDTO {
    private long id;
    private String name;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String description;
    private String instructorBiography;
    private int maxNumberOfPeople;
    private String behaviorRules;
    private String fishingGear;
    private double pricePerDay;
    private ReservationCancellation cancellation;
    private AppUserDTO instructor;
    private double averageGrade;
    private Set<TimeRangeDTO> freePeriods;
    private Set<ActionDTO> actions;
    private Set<AdditionalServiceDTO> additionalServices;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public double getAverageGrade() {
        return averageGrade;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public Set<TimeRangeDTO> getFreePeriods() { return freePeriods; }

    public Set<ActionDTO> getActions() { return actions; }

    public AdventureDTO() {}

    public AdventureDTO(Adventure adventure) {
        this.id = adventure.getId();
        this.name = adventure.getName();
        this.city = adventure.getLocation().getCity();
        this.street = adventure.getLocation().getStreet();
        this.number = adventure.getLocation().getNumber();
        this.zipCode = adventure.getLocation().getZipCode();
        this.latitude = adventure.getLocation().getLatitude();
        this.longitude = adventure.getLocation().getLongitude();
        this.description = adventure.getDescription();
        this.instructorBiography = adventure.getInstructorBiography();
        this.maxNumberOfPeople = adventure.getMaxNumberOfPeople();
        this.behaviorRules = adventure.getBehaviorRules();
        this.fishingGear = adventure.getFishingGear();
        this.pricePerDay = adventure.getPricePerDay();
        this.cancellation = adventure.getCancellation();
        this.instructor = new AppUserDTO(adventure.getInstructor());
        this.averageGrade = adventure.getAverageGrade();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : adventure.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.freePeriods = new HashSet<>();
        for(TimeRange timeRange : adventure.getFreePeriods()) {
            this.freePeriods.add(new TimeRangeDTO(timeRange));
        }
        this.actions = new HashSet<>();
        for(Action action: adventure.getActions()) {
            this.actions.add(new ActionDTO(action));
        }
    }

    public AdventureDTO(long id, String name, String city, String street, String number, String zipCode, String latitude, String longitude, String description, String instructorBiography, int maxNumberOfPeople, String behaviorRules, String fishingGear, double pricePerDay, ReservationCancellation cancellation, AppUserDTO instructor, double averageGrade, Set<AdditionalServiceDTO> additionalServices,  Set<ActionDTO> actions, Set<TimeRangeDTO> freePeriods) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviorRules = behaviorRules;
        this.fishingGear = fishingGear;
        this.pricePerDay = pricePerDay;
        this.cancellation = cancellation;
        this.instructor = instructor;
        this.averageGrade = averageGrade;
        this.additionalServices = additionalServices;
        this.freePeriods = freePeriods;
        this.actions = actions;
    }
}
