package com.isa.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrator extends AppUser {

    @Column
    private boolean admin;
    @Column
    private boolean firstReg;

    public Administrator() {
    }

    public Administrator(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.ADMIN);
        this.admin = false;
        this.firstReg = true;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isFirstReg() {
        return firstReg;
    }

    public void setFirstReg(boolean firstReg) {
        this.firstReg = firstReg;
    }
}
