package com.isa.project.dto;

import com.isa.project.model.*;

import java.util.HashSet;
import java.util.Set;

public class CottageDTO {
    private long id;
    private String name;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String description;
    private String behaviorRules;
    private AppUserDTO cottageOwner;
    private double pricePerDay;
    private int maxNumberOfPeople;
    private double averageGrade;
    private Set<AdditionalServiceDTO> additionalServices;
    private Set<TimeRangeDTO> freePeriods;
    private Set<ActionDTO> actions;
    private Set<RoomDTO> rooms;

    public CottageDTO() { }

    public CottageDTO(Cottage cottage) {
        this.id = cottage.getId();
        this.name = cottage.getName();
        if (cottage.getLocation() != null)
        {
            this.city = cottage.getLocation().getCity();
            this.street = cottage.getLocation().getStreet();
            this.number = cottage.getLocation().getNumber();
            this.zipCode = cottage.getLocation().getZipCode();
            this.latitude = cottage.getLocation().getLatitude();
            this.longitude = cottage.getLocation().getLongitude();
        }
        this.description = cottage.getDescription();
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
        for(TimeRange timeRange : cottage.getAllPeriods()) {
            this.freePeriods.add(new TimeRangeDTO(timeRange));
        }
        this.actions = new HashSet<>();
        for(Action action: cottage.getActions()) {
            this.actions.add(new ActionDTO(action));
        }

        this.rooms = new HashSet<>();
        for(Room room: cottage.getRooms()) {
            this.rooms.add(new RoomDTO(room));
        }
    }

    public CottageDTO(long id, String name, String city, String street, String number, String zipCode, String latitude, String longitude, String description, Set<RoomDTO> rooms, String behaviorRules, AppUserDTO cottageOwner, double pricePerDay, int maxNumberOfPeople, double averageGrade, Set<AdditionalServiceDTO> additionalServices, Set<TimeRangeDTO> freePeriods, Set<ActionDTO> actions) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.behaviorRules = behaviorRules;
        this.cottageOwner = cottageOwner;
        this.pricePerDay = pricePerDay;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.averageGrade = averageGrade;
        this.additionalServices = additionalServices;
        this.freePeriods = freePeriods;
        this.actions = actions;
        this.rooms = rooms;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getCity() { return city; }

    public String getStreet() { return street; }

    public String getNumber() { return number; }

    public String getZipCode() { return zipCode; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public String getDescription() { return description; }

    public String getBehaviorRules() { return behaviorRules; }

    public AppUserDTO getCottageOwner() { return cottageOwner; }

    public double getPricePerDay() { return pricePerDay; }

    public int getMaxNumberOfPeople() { return maxNumberOfPeople; }

    public double getAverageGrade() { return averageGrade; }

    public Set<AdditionalServiceDTO> getAdditionalServices() { return additionalServices; }

    public Set<TimeRangeDTO> getFreePeriods() { return freePeriods; }

    public Set<ActionDTO> getActions() { return actions; }

    public Set<RoomDTO> getRooms() { return rooms; }
}
