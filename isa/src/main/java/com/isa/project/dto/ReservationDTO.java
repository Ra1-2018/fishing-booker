package com.isa.project.dto;

import com.isa.project.model.Reservation;

import java.util.Date;

public class ReservationDTO {
    private long id;
    private Date reservationStartDateAndTime;
    private int durationInDays;
    private int maxPeople;
    private String additionalServices;
    private double price;
    private AppUserDTO client;
    private ServiceDTO service;
    private String location;

    public ReservationDTO() {}

    public ReservationDTO(long id, Date reservationStartDateAndTime, int durationInDays, int maxPeople, String additionalServices, double price, AppUserDTO client, ServiceDTO service, String location) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.client = client;
        this.service = service;
        this.location = location;
    }

    public ReservationDTO(Reservation reservation) { this(reservation.getId(), reservation.getReservationStartDateAndTime(), reservation.getDurationInDays(), reservation.getMaxPeople(), reservation.getAdditionalServices(), reservation.getPrice(), new AppUserDTO(reservation.getClient()), new ServiceDTO(reservation.getService()), reservation.getLocation());}

    public long getId() {
        return id;
    }

    public Date getReservationStartDateAndTime() {
        return reservationStartDateAndTime;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getAdditionalServices() {
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
