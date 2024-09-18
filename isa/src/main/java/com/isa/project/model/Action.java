package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date startTime;

    @Column
    private int durationInDays;

    @Column
    private int maxNumberOfPeople;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "action_additional_service",
            joinColumns = { @JoinColumn(name = "action_id") },
            inverseJoinColumns = { @JoinColumn(name = "additional_service_id") }
    )
    private Set<AdditionalService> additionalServices = new HashSet<>();

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    public Action() {}

    public Action(long id, Date startTime, int durationInDays, int maxNumberOfPeople, Set<AdditionalService> additionalServices, double price, Service service) {
        this.id = id;
        this.startTime = startTime;
        this.durationInDays = durationInDays;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.additionalServices = additionalServices;
        this.service = service;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public void addAdditionalService(AdditionalService additionalService) {
        additionalServices.add(additionalService);
    }

    public void removeAdditionalService(AdditionalService additionalService) {
        additionalServices.remove(additionalService);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        double pricePerDay = service.getPricePerDay();
        for(AdditionalService additionalService : additionalServices) {
            pricePerDay += additionalService.getPrice();
        }
        return pricePerDay * durationInDays;
    }

    public double getDiscount() {
        return 1 - getPrice()/getOriginalPrice();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
