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
    private Set<CottageReservation> cottageReservations = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BoatReservation> boatReservations = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdventureReservation> adventureReservations = new HashSet<>();

    public Client() {}

    public Client(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone);
    }

    public Set<CottageReservation> getCottageReservations() { return cottageReservations; }

    public void setCottageReservations(Set<CottageReservation> cottageReservations) { this.cottageReservations = cottageReservations; }

    public Set<BoatReservation> getBoatReservations() { return boatReservations; }

    public void setBoatReservations(Set<BoatReservation> boatReservations) { this.boatReservations = boatReservations; }

    public Set<AdventureReservation> getAdventureReservations() { return adventureReservations; }

    public void setAdventureReservations(Set<AdventureReservation> adventureReservations) { this.adventureReservations = adventureReservations; }
}
