package com.isa.project.dto;

import com.isa.project.model.DeletionRequest;

import java.util.Date;

public class DeletionRequestDTO {
    private long id;
    private String userEmail;
    private String explanation;
    private Date dateSubmitted;

    public DeletionRequestDTO() {}

    public DeletionRequestDTO(long id, String userEmail, String explanation, Date dateSubmitted) {
        this.id = id;
        this.userEmail = userEmail;
        this.explanation = explanation;
        this.dateSubmitted = dateSubmitted;
    }

    public DeletionRequestDTO(DeletionRequest deletionRequest) { this(deletionRequest.getId(), deletionRequest.getUserEmail(), deletionRequest.getExplanation(), deletionRequest.getDateSubmitted()); }

    public long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getExplanation() {
        return explanation;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }
}
