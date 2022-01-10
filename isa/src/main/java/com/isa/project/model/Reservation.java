package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Date reservationStartDateAndTime;
    @Column
    private String location;
    @Column
    private int durationInDays;
    @Column
    private int maxPeople;
    @Column
    private String additionalServices;
    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Reservation() {
    }

    public Reservation(long id, Date reservationStartDateAndTime, String location, int durationInDays, int maxPeople, String additionalServices, double price, Service service, Client client) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.location = location;
        this.durationInDays = durationInDays;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.service = service;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getReservationStartDateAndTime() {
        return reservationStartDateAndTime;
    }

    public void setReservationStartDateAndTime(Date reservationStartDateAndTime) {
        this.reservationStartDateAndTime = reservationStartDateAndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
