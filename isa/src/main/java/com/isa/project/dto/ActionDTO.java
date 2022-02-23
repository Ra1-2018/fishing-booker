package com.isa.project.dto;

import com.isa.project.model.Action;
import com.isa.project.model.AdditionalService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ActionDTO {
    private long id;
    private Date startTime;
    private int durationInDays;
    private int maxNumberOfPeople;
    private Set<AdditionalServiceDTO> additionalServices;
    private double price;
    private ServiceDTO service;

    public ActionDTO() {}

    public ActionDTO(long id, Date startTime, int durationInDays, int maxNumberOfPeople, Set<AdditionalServiceDTO> additionalServices, double price, ServiceDTO service) {
        this.id = id;
        this.startTime = startTime;
        this.durationInDays = durationInDays;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.service = service;
    }

    public ActionDTO(Action action) {
        this.id = action.getId();
        this.startTime = action.getStartTime();
        this.durationInDays = action.getDurationInDays();
        this.maxNumberOfPeople = action.getMaxNumberOfPeople();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : action.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.price = action.getPrice();
        this.service = new ServiceDTO(action.getService());
    }

    public long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public ServiceDTO getService() {
        return service;
    }

    public double getOriginalPrice() {
        double pricePerDay = service.getPricePerDay();
        for(AdditionalServiceDTO additionalService : additionalServices) {
            pricePerDay += additionalService.getPrice();
        }
        return pricePerDay * durationInDays;
    }

    public double getDiscount() {
        return 1 - getPrice()/getOriginalPrice();
    }
}
