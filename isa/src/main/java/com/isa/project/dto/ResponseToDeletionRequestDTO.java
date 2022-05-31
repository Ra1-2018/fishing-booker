package com.isa.project.dto;

import com.isa.project.model.ResponseToDeletionRequest;

public class ResponseToDeletionRequestDTO {

    private Long id;
    private String explanation;
    private Long userID;
    private Long requestID;
    private boolean approved;

    public ResponseToDeletionRequestDTO() { }

    public ResponseToDeletionRequestDTO(ResponseToDeletionRequest response) {
        this(response.getId(), response.getContent(), response.getAdministrator().getId(), response.getDeletionRequest().getId(), response.isApproved());
    }

    public ResponseToDeletionRequestDTO(Long id, String explanation, Long userID, Long requestID, boolean approved) {
        this.id = id;
        this.explanation = explanation;
        this.userID = userID;
        this.requestID = requestID;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public String getExplanation() {
        return explanation;
    }

    public Long getUserID() {
        return userID;
    }

    public Long getRequestID() {
        return requestID;
    }

    public boolean isApproved() {
        return approved;
    }
}
