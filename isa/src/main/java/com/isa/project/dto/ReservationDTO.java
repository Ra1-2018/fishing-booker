package com.isa.project.dto;

import com.isa.project.model.AdditionalService;
import com.isa.project.model.Reservation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReservationDTO {
    private long id;
    private Date reservationStartDateAndTime;
    private int durationInDays;
    private int numberOfPeople;
    private Set<AdditionalServiceDTO> additionalServices;
    private double price;
    private AppUserDTO client;
    private ServiceDTO service;
    private String location;

    public ReservationDTO() {}

    public ReservationDTO(long id, Date reservationStartDateAndTime, int durationInDays, int numberOfPeople, Set<AdditionalServiceDTO> additionalServices, double price, AppUserDTO client, ServiceDTO service, String location) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.numberOfPeople = numberOfPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.client = client;
        this.service = service;
        this.location = location;
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.reservationStartDateAndTime = reservation.getReservationStartDateAndTime();
        this.durationInDays = reservation.getDurationInDays();
        this.numberOfPeople = reservation.getNumberOfPeople();
        this.additionalServices = new HashSet<>();
        for(AdditionalService additionalService : reservation.getAdditionalServices()) {
            this.additionalServices.add(new AdditionalServiceDTO(additionalService));
        }
        this.price = reservation.getPrice();
        this.client = new AppUserDTO(reservation.getClient());
        this.service = new ServiceDTO(reservation.getService());
        this.location = reservation.getLocation();
    }

    public long getId() {
        return id;
    }

    public Date getReservationStartDateAndTime() {
        return reservationStartDateAndTime;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public AppUserDTO getClient() {
        return client;
    }

    public ServiceDTO getService() {
        return service;
    }

    public String getLocation() {
        return location;
    }
}
