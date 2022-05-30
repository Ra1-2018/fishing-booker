package com.isa.project.model;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String zipCode;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    public Location() {}

    public Location(Long id, String city, String street, String number, String zipCode, String latitude, String longitude, Service service) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
