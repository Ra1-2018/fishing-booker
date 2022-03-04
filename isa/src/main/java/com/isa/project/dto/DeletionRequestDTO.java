package com.isa.project.dto;

import com.isa.project.model.DeletionRequest;

public class DeletionRequestDTO {
    private long id;
    private String userEmail;
    private String explanation;

    public DeletionRequestDTO() {}

    public DeletionRequestDTO(long id, String userEmail, String explanation) {
        this.id = id;
        this.userEmail = userEmail;
        this.explanation = explanation;
    }

    public DeletionRequestDTO(DeletionRequest deletionRequest) { this(deletionRequest.getId(), deletionRequest.getUserEmail(), deletionRequest.getExplanation()); }

    public long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getExplanation() {
        return explanation;
    }
}
