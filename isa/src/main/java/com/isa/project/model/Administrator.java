package com.isa.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Administrator extends AppUser {

    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ResponseToComplaint> responsesToComplaints = new HashSet<>();

    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ResponseToDeletionRequest> responsesToDeletionRequests = new HashSet<>();
    @Column
    private boolean admin;
    @Column
    private boolean firstReg;

    public Administrator() {
        super();
    }

    public Administrator(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, Set<ResponseToComplaint> responsesToComplaints, Set<ResponseToDeletionRequest> responsesToDeletionRequests) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.ADMIN);
        this.responsesToComplaints = responsesToComplaints;
        this.responsesToDeletionRequests = responsesToDeletionRequests;
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
