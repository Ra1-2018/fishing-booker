package com.isa.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends AppUser{

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    public Client() {
        super();
    }

    public Client(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, Set<Reservation> reservations) {
        super(id, email, password, name, surname, address, city, country, telephone);
        this.reservations = reservations;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
