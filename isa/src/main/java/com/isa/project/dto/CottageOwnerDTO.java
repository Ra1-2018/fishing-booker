package com.isa.project.dto;

import com.isa.project.model.CottageOwner;

public class CottageOwnerDTO {

    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String telephone;

    public CottageOwnerDTO() { }

    public CottageOwnerDTO(CottageOwner cottageOwner) {
        this(cottageOwner.getId(), cottageOwner.getEmail(), cottageOwner.getPassword(), cottageOwner.getName(), cottageOwner.getSurname(), cottageOwner.getAddress(), cottageOwner.getCity(), cottageOwner.getCountry(), cottageOwner.getTelephone());
    }

    public CottageOwnerDTO(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
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

}
