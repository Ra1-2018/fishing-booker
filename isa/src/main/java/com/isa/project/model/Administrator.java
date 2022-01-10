package com.isa.project.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends AppUser {

    public Administrator() {
    }

    public Administrator(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.ADMIN);
    }
}
