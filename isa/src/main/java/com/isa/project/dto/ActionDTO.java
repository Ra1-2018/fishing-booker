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
    private double originalPrice;
    private double discount;
    private ServiceDTO service;

    public ActionDTO() {}

    public ActionDTO(long id, Date startTime, int durationInDays, int maxNumberOfPeople, Set<AdditionalService> additionalServices, double originalPrice, double discount, ServiceDTO service) {
        this.id = id;
        this.startTime = startTime;
        this.durationInDays = durationInDays;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : additionalServices) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.service = service;
    }

    public ActionDTO(Action action) {this(action.getId(), action.getStartTime(), action.getDurationInDays(), action.getMaxNumberOfPeople(), action.getAdditionalServices(), action.getOriginalPrice(), action.getDiscount(), new ServiceDTO(action.getService())); }

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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return originalPrice * (1 - discount);
    }

    public ServiceDTO getService() {
        return service;
    }
}
