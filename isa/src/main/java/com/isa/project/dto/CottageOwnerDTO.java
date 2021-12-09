package com.isa.project.dto;

import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.OwnerType;

import java.util.HashSet;
import java.util.Set;

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
    private OwnerType ownerType;

    public CottageOwnerDTO() { }

    public CottageOwnerDTO(CottageOwner cottageOwner) {
        this(cottageOwner.getId(), cottageOwner.getEmail(), cottageOwner.getPassword(), cottageOwner.getName(), cottageOwner.getSurname(), cottageOwner.getAddress(), cottageOwner.getCity(), cottageOwner.getCountry(), cottageOwner.getTelephone(), cottageOwner.getType());
    }

    public CottageOwnerDTO(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, OwnerType ownerType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.ownerType = ownerType;
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


    public OwnerType getOwnerType() {
        return ownerType;
    }

}
