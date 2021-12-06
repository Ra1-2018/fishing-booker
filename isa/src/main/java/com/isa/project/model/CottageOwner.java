package com.isa.project.model;

public class CottageOwner extends User{

    private OwnerType type;

    public CottageOwner(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, OwnerType type) {
        super(id, email, password, name, surname, address, city, country, telephone);
        this.type = type;
    }
}
