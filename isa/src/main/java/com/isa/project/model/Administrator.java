package com.isa.project.model;

public class Administrator extends User{

    public Administrator(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone);
    }
}
