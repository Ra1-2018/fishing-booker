package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoatOwner extends AppUser {

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Boat> boats = new HashSet<>();

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
    private Set<Penalty> penalties;

    public BoatOwner() {
    }

    public BoatOwner(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.BOAT_OWNER);
        this.boats = new HashSet<>();
        this.reports = new HashSet<>();
        this.penalties = new HashSet<>();
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    public void addBoat(Boat boat) {
        boats.add(boat);
        boat.setBoatOwner(this);
    }

    public void removeBoat(Boat boat) {
        boats.remove(boat);
        boat.setBoatOwner(null);
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(Set<Penalty> penalties) {
        this.penalties = penalties;
    }
}
