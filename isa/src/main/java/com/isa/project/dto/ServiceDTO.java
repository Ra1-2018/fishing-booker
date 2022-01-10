package com.isa.project.dto;

import com.isa.project.model.Service;
import com.isa.project.model.ServiceType;

public class ServiceDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private String priceList;
    private String behaviorRules;
    private ServiceType serviceType;

    public ServiceDTO() {}

    public ServiceDTO(long id, String name, String address, String description, String priceList, String behaviorRules, ServiceType serviceType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.priceList = priceList;
        this.behaviorRules = behaviorRules;
        this.serviceType = serviceType;
    }

    public ServiceDTO(Service service) { this(service.getId(), service.getName(), service.getAddress(), service.getDescription(), service.getPriceList(), service.getBehaviorRules(), service.getServiceType()); }

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

    public String getPriceList() {
        return priceList;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }
}
