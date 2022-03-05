package com.isa.project.model;

import javax.persistence.CascadeType;
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

    public Administrator() {
        super();
    }

    public Administrator(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, Set<ResponseToComplaint> responsesToComplaints, Set<ResponseToDeletionRequest> responsesToDeletionRequests) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.ADMIN);
        this.responsesToComplaints = responsesToComplaints;
        this.responsesToDeletionRequests = responsesToDeletionRequests;
    }
}
