package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor extends AppUser {

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures = new HashSet<Adventure>();

    public Instructor() { }

    public Instructor(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone) {
        super(id, email, password, name, surname, address, city, country, telephone, AppUserType.INSTRUCTOR);
        this.adventures = new HashSet<Adventure>();
    }

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }

    public void addAdventure(Adventure adventure) {
        adventures.add(adventure);
        adventure.setInstructor(this);
    }

    public void removeAdventure(Adventure adventure) {
        adventures.remove(adventure);
        adventure.setInstructor(null);
    }
}
