package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BoatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Date reservationStartDateAndTime;
    @Column
    private int durationInDays;
    @Column
    private int maxPeople;
    @Column
    private String additionalServices;
    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public BoatReservation() {
    }

    public BoatReservation(long id, Date reservationStartDateAndTime, int durationInDays, int maxPeople, String additionalServices, double price) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
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

    public void setReservationStartDateAndTime(Date reservationStartDateAndTime) { this.reservationStartDateAndTime = reservationStartDateAndTime; }

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

    public Boat getBoat() { return boat; }

    public void setBoat(Boat boat) { this.boat = boat; }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }
}
