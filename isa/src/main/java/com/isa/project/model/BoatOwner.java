package com.isa.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BoatOwner extends User{

    @Column
    private OwnerType type;

    public BoatOwner() {
    }

    public BoatOwner(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, OwnerType type) {
        super(id, email, password, name, surname, address, city, country, telephone);
        this.type = type;
    }
}
