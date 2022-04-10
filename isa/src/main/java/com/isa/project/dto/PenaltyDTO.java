package com.isa.project.dto;

import com.isa.project.model.Penalty;

import java.util.Date;

public class PenaltyDTO {
    private long id;
    private Date dateIssued;
    private AppUserDTO client;
    private AppUserDTO owner;

    public PenaltyDTO() { }

    public PenaltyDTO(Penalty penalty) {
        this.id = penalty.getId();
        this.dateIssued = new Date();
        this.client = new AppUserDTO(penalty.getClient());
        if(penalty.getBoatOwner() != null) {
            this.owner = new AppUserDTO(penalty.getBoatOwner());
        }
        else if(penalty.getCottageOwner() != null) {
            this.owner = new AppUserDTO(penalty.getCottageOwner());
        }
        else if(penalty.getInstructor() != null) {
            this.owner = new AppUserDTO(penalty.getInstructor());
        }
    }

    public PenaltyDTO(long id, Date dateIssued, AppUserDTO client, AppUserDTO owner) {
        this.id = id;
        this.dateIssued = dateIssued;
        this.client = client;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public AppUserDTO getClient() {
        return client;
    }

    public AppUserDTO getOwner() {
        return owner;
    }
}
