package com.isa.project.dto;

import com.isa.project.model.Service;
import com.isa.project.model.ServiceType;

public class ServiceDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private double pricePerDay;
    private String behaviorRules;
    private ServiceType serviceType;
    private int maxNumberOfPeople;

    public ServiceDTO() {}

    public ServiceDTO(long id, String name, String address, String description, double pricePerDay, String behaviorRules, ServiceType serviceType, int maxNumberOfPeople) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.behaviorRules = behaviorRules;
        this.serviceType = serviceType;
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public ServiceDTO(Service service) { this(service.getId(), service.getName(), service.getAddress(), service.getDescription(), service.getPricePerDay(), service.getBehaviorRules(), service.getServiceType(), service.getMaxNumberOfPeople()); }

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
}
