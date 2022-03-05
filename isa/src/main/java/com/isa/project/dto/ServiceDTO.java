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
    private String address;
    private String description;
    private double pricePerDay;
    private String behaviorRules;
    private ServiceType serviceType;
    private int maxNumberOfPeople;
    private Set<AdditionalServiceDTO> additionalServices;
    private double averageGrade;

    public ServiceDTO() {}

    public ServiceDTO(long id, String name, String address, String description, double pricePerDay, String behaviorRules, ServiceType serviceType, int maxNumberOfPeople, Set<AdditionalServiceDTO> additionalServices, double averageGrade) {
        this.id = id;
        this.name = name;
        this.address = address;
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
        this.address = service.getAddress();
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

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}
