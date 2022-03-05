package com.isa.project.dto;

import com.isa.project.model.Client;
import com.isa.project.model.Review;
import com.isa.project.model.Service;

import java.util.Date;

public class ReviewDTO {
    private long id;
    private double grade;
    private String revision;
    private AppUserDTO client;
    private ServiceDTO service;
    private boolean approved;
    private Date dateSubmitted;

    public ReviewDTO() {}

    public ReviewDTO(long id, double grade, String revision, AppUserDTO client, ServiceDTO service, boolean approved, Date dateSubmitted) {
        this.id = id;
        this.grade = grade;
        this.revision = revision;
        this.client = client;
        this.service = service;
        this.approved = approved;
        this.dateSubmitted = dateSubmitted;
    }

    public ReviewDTO(Review review) { this(review.getId(), review.getGrade(), review.getRevision(), new AppUserDTO(review.getClient()), new ServiceDTO(review.getService()), review.isApproved(), review.getDateSubmitted()); }

    public long getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }

    public String getRevision() {
        return revision;
    }

    public AppUserDTO getClient() {
        return client;
    }

    public ServiceDTO getService() {
        return service;
    }

    public boolean isApproved() {
        return approved;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }
}
