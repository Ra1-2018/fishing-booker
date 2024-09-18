package com.isa.project.dto;

import com.isa.project.model.AppUserType;

public class AppUserSpecialDTO {
    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String telephone;
    private AppUserType appUserType;
    private String explanation;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    public AppUserType getAppUserType() {
        return appUserType;
    }

    public String getExplanation() {
        return explanation;
    }

    public AppUserSpecialDTO() { }

    public AppUserSpecialDTO(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, AppUserType appUserType, String explanation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.appUserType = appUserType;
        this.explanation = explanation;
    }
}
