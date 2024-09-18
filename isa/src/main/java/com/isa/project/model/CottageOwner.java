package com.isa.project.model;

import com.isa.project.service.CottageOwnerService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CottageOwner extends AppUser {

    @OneToMany(mappedBy = "cottageOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cottage> cottages = new HashSet<Cottage>();

    @OneToMany(mappedBy = "cottageOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();

    @OneToMany(mappedBy = "cottageOwner", fetch = FetchType.LAZY)
    private Set<Penalty> penalties = new HashSet<>();

    public CottageOwner() { }

    public CottageOwner(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.COTTAGE_OWNER);
        this.reports = new HashSet<>();
        this.penalties = new HashSet<>();
    }

    public Set<Cottage> getCottages() { return cottages; }

    public void setCottages(Set<Cottage> cottages) { this.cottages = cottages; }


    public void addCottage(Cottage cottage) {
        cottages.add(cottage);
        cottage.setCottageOwner(this);
    }

    public void removeCottage(Cottage cottage) {
        cottages.remove(cottage);
        cottage.setCottageOwner(null);
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
