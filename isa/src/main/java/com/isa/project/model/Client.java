package com.isa.project.model;

import javax.persistence.Entity;

@Entity
public class Client extends AppUser{

    public Client() {}

    public Client(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone);
    }
}
