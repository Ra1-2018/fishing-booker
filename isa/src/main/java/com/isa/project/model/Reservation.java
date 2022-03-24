package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Date reservationStartDateAndTime;
    @Column
    private int durationInDays;
    @Column
    private int numberOfPeople;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "reservation_additional_service",
            joinColumns = { @JoinColumn(name = "reservation_id") },
            inverseJoinColumns = { @JoinColumn(name = "additional_service_id") }
    )
    private Set<AdditionalService> additionalServices = new HashSet<>();
    @Column
    private double price; //racunica broja dana i cene po danu ( i broja ljudi)

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Reservation() {
    }

    public Reservation(long id, Date reservationStartDateAndTime, int durationInDays, int numberOfPeople, Set<AdditionalService> additionalServices, double price, Service service, Client client) {
        this.id = id;
        this.reservationStartDateAndTime = reservationStartDateAndTime;
        this.durationInDays = durationInDays;
        this.numberOfPeople = numberOfPeople;
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

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
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

    public void addAdditionalService(AdditionalService additionalService) {
        additionalServices.add(additionalService);
        additionalService.getReservations().add(this);
    }
}
