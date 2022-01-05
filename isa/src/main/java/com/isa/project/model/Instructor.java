package com.isa.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Instructor extends AppUser {

    public Instructor() { }

    public Instructor(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone);
    }
}
