package com.isa.project.model;

public class BoatOwner extends User{

    private OwnerType type;

    public BoatOwner(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, OwnerType type) {
        super(id, email, password, name, surname, address, city, country, telephone);
        this.type = type;
    }
}
