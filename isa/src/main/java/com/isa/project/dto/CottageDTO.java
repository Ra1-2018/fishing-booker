package com.isa.project.dto;

import com.isa.project.model.AdditionalService;
import com.isa.project.model.Cottage;
import com.isa.project.model.TimeRange;
import com.isa.project.model.Action;

import java.util.HashSet;
import java.util.Set;

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
    private double averageGrade;
    private Set<AdditionalServiceDTO> additionalServices;
    private Set<TimeRangeDTO> freePeriods;
    private Set<ActionDTO> actions;

    public CottageDTO() { }

    public CottageDTO(Cottage cottage) {
        this.id = cottage.getId();
        this.name = cottage.getName();
        this.address = cottage.getAddress();
        this.description = cottage.getDescription();
        this.roomsTotalNumber = cottage.getRoomsTotalNumber();
        this.behaviorRules = cottage.getBehaviorRules();
        this.cottageOwner = new AppUserDTO(cottage.getCottageOwner());
        this.pricePerDay = cottage.getPricePerDay();
        this.maxNumberOfPeople = cottage.getMaxNumberOfPeople();
        this.averageGrade = cottage.getAverageGrade();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : cottage.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.freePeriods = new HashSet<>();
        for(TimeRange timeRange : cottage.getFreePeriods()) {
            this.freePeriods.add(new TimeRangeDTO(timeRange));
        }
        this.actions = new HashSet<>();
        for(Action action: cottage.getActions()) {
            this.actions.add(new ActionDTO(action));
        }
    }

    public CottageDTO(long id, String name, String address, String description, int roomsTotalNumber, String behaviorRules, AppUserDTO cottageOwner, double pricePerDay, int maxNumberOfPeople, double averageGrade, Set<AdditionalServiceDTO> additionalServices, Set<TimeRangeDTO> freePeriods, Set<ActionDTO> actions) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomsTotalNumber = roomsTotalNumber;
        this.behaviorRules = behaviorRules;
        this.cottageOwner = cottageOwner;
        this.pricePerDay = pricePerDay;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.averageGrade = averageGrade;
        this.additionalServices = additionalServices;
        this.freePeriods = freePeriods;
        this.actions = actions;
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

    public double getAverageGrade() {
        return averageGrade;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public Set<TimeRangeDTO> getFreePeriods() { return freePeriods; }

    public Set<ActionDTO> getActions() { return actions; }
}
