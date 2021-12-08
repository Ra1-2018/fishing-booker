package com.isa.project.dto;

import com.isa.project.model.AppUser;

public class AppUserDTO {
    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String telephone;

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

    public AppUserDTO(AppUser appUser) { this(appUser.getId(), appUser.getEmail(), appUser.getPassword(), appUser.getName(), appUser.getSurname(), appUser.getAddress(), appUser.getCity(), appUser.getCountry(), appUser.getTelephone()); }

    public AppUserDTO(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
    }
}
