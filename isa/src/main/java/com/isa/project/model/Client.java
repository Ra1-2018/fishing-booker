package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends AppUser{

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "subscription",
            joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") }
    )
    private Set<Service> subscriptions = new HashSet<>();

    public Client() {
        super();
    }

    public Client(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, Set<Reservation> reservations, Set<Review> reviews, Set<Service> subscriptions) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.CLIENT);
        this.reservations = reservations;
        this.reviews = reviews;
        this.subscriptions = subscriptions;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Service> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Service> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(Service subscription) {
        subscriptions.add(subscription);
    }

    public void removeSubscription(Service subscription) {
        for(Service service : subscriptions) {
            if(service.getId() == subscription.getId()) {
                subscriptions.remove(service);
            }
        }
    }
}
