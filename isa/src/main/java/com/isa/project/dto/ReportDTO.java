package com.isa.project.dto;

import com.isa.project.model.Report;

public class ReportDTO {
    private long id;
    private String comment;
    private boolean sanctionRequested;
    private AppUserDTO client;
    private AppUserDTO owner;

    private boolean approved;

    public ReportDTO() {

    }

    public ReportDTO(Report report) {
        this.id = report.getId();
        this.comment = report.getComment();
        this.sanctionRequested = report.isSanctionRequested();
        this.client = new AppUserDTO(report.getClient());
        this.approved = report.isApproved();

        if(report.getCottageOwner() != null) {
            this.owner = new AppUserDTO(report.getCottageOwner());
        }
        else if(report.getBoatOwner() != null) {
            this.owner = new AppUserDTO(report.getBoatOwner());
        }
        else if(report.getInstructor() != null) {
            this.owner = new AppUserDTO(report.getInstructor());
        }
    }


    public ReportDTO(long id, String comment, boolean sanctionRequested, AppUserDTO client, AppUserDTO owner) {
        this.id = id;
        this.comment = comment;
        this.sanctionRequested = sanctionRequested;
        this.client = client;
        this.owner = owner;
        this.approved = false;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public boolean isSanctionRequested() {
        return sanctionRequested;
    }

    public AppUserDTO getClient() {
        return client;
    }

    public AppUserDTO getOwner() {
        return owner;
    }

    public boolean isApproved() {
        return approved;
    }
}
