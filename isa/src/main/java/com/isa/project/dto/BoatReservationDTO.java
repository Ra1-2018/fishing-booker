package com.isa.project.dto;

import com.isa.project.model.BoatReservation;

import java.util.Date;

public class BoatReservationDTO {
    private long id;
    private Date reservationStartDateAndTime;
    private int durationInDays;
    private int maxPeople;
    private String additionalServices;
    private double price;
    private AppUserDTO client;
    private BoatDTO boat;

    public BoatReservationDTO() {
    }

    public BoatReservationDTO(BoatReservation reservation) { this(reservation.getId(), reservation.getReservationStartDateAndTime(), reservation.getDurationInDays(), reservation.getMaxPeople(), reservation.getAdditionalServices(), reservation.getPrice(), new AppUserDTO(reservation.getClient()), new BoatDTO(reservation.getBoat())); }

    public BoatReservationDTO(long id, Date reservationStartDateAndTime, int durationInDays, int maxPeople, String additionalServices, double price, AppUserDTO client, BoatDTO boat) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.client = client;
        this.boat = boat;
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

    public BoatDTO getBoat() {
        return boat;
    }
}
