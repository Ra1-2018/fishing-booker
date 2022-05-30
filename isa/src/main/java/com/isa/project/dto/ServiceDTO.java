package com.isa.project.dto;

import com.isa.project.model.AdditionalService;
import com.isa.project.model.Service;
import com.isa.project.model.ServiceType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceDTO {
    private long id;
    private String name;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String description;
    private double pricePerDay;
    private String behaviorRules;
    private ServiceType serviceType;
    private int maxNumberOfPeople;
    private Set<AdditionalServiceDTO> additionalServices;
    private double averageGrade;

    public ServiceDTO() {}

    public ServiceDTO(long id, String name, String city, String street, String number, String zipCode, String latitude, String longitude, String description, double pricePerDay, String behaviorRules, ServiceType serviceType, int maxNumberOfPeople, Set<AdditionalServiceDTO> additionalServices, double averageGrade) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.behaviorRules = behaviorRules;
        this.serviceType = serviceType;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.additionalServices = additionalServices;
        this.averageGrade = averageGrade;
    }

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.city = service.getLocation().getCity();
        this.street = service.getLocation().getStreet();
        this.number = service.getLocation().getNumber();
        this.zipCode = service.getLocation().getZipCode();
        this.latitude = service.getLocation().getLatitude();
        this.longitude = service.getLocation().getLongitude();
        this.description = service.getDescription();
        this.pricePerDay = service.getPricePerDay();
        this.behaviorRules = service.getBehaviorRules();
        this.serviceType = service.getServiceType();
        this.maxNumberOfPeople = service.getMaxNumberOfPeople();
        this.averageGrade = service.getAverageGrade();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : service.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
    }

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

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}
