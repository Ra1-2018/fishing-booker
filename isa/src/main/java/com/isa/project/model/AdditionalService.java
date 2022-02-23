package com.isa.project.model;

import com.isa.project.dto.AdditionalServiceDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private long price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToMany(mappedBy = "additionalServices", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    @ManyToMany(mappedBy = "additionalServices")
    private Set<Action> actions;

    public AdditionalService() {}

    public AdditionalService(long id, String name, long price, Service service, Set<Reservation> reservations, Set<Action> actions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.service = service;
        this.reservations = reservations;
        this.actions = actions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
