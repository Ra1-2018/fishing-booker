package com.isa.project.dto;

import com.isa.project.model.CottageReservation;

import java.util.Date;

public class CottageReservationDTO {
    private long id;
    private Date reservationStartDateAndTime;
    private int durationInDays;
    private int maxPeople;
    private String additionalServices;
    private double price;
    private AppUserDTO client;
    private CottageDTO cottage;

    public CottageReservationDTO() { }

    public CottageReservationDTO(CottageReservation reservation) { this(reservation.getId(), reservation.getReservationStartDateAndTime(), reservation.getDurationInDays(), reservation.getMaxPeople(), reservation.getAdditionalServices(), reservation.getPrice(), new AppUserDTO(reservation.getClient()), new CottageDTO(reservation.getCottage())); }

    public CottageReservationDTO(long id, Date reservationStartDateAndTime, int durationInDays, int maxPeople, String additionalServices, double price, AppUserDTO client, CottageDTO cottage) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
        this.client = client;
        this.cottage = cottage;
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

    public CottageDTO getCottage() {
        return cottage;
    }
}
