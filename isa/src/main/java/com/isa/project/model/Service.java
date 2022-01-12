package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Service {
    @Id
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String behaviorRules;
    @Column
    private String priceList;
    @Column
    private String address;
    @Column
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TimeRange> freePeriods = new HashSet<>();

    public Service() {}

    public Service(long id, String name, String description, String behaviorRules, String priceList, String address, ServiceType serviceType, Set<Reservation> reservations, Set<TimeRange> freePeriods) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.behaviorRules = behaviorRules;
        this.priceList = priceList;
        this.address = address;
        this.serviceType = serviceType;
        this.reservations = reservations;
        this.freePeriods = freePeriods;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<TimeRange> getFreePeriods() { return freePeriods; }

    public void setFreePeriods(Set<TimeRange> freePeriods) { this.freePeriods = freePeriods; }
}
