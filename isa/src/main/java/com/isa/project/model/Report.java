package com.isa.project.model;

import javax.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String comment;
    @Column
    private boolean sanctionRequested;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner cottageOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column
    private boolean approved;

    public Report() {

    }
    public Report(long id, String comment, boolean sanctionRequested, Client client, CottageOwner cottageOwner, BoatOwner boatOwner, Instructor instructor) {
        this.id = id;
        this.comment = comment;
        this.sanctionRequested = sanctionRequested;
        this.client = client;
        this.cottageOwner = cottageOwner;
        this.boatOwner = boatOwner;
        this.instructor = instructor;
        this.approved = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSanctionRequested() {
        return sanctionRequested;
    }

    public void setSanctionRequested(boolean sanctionRequested) {
        this.sanctionRequested = sanctionRequested;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CottageOwner getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
