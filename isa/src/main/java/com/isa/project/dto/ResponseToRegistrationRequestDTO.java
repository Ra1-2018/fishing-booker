package com.isa.project.dto;

import com.isa.project.model.ResponseToRegistrationRequest;

public class ResponseToRegistrationRequestDTO {

    private Long id;
    private String explanation;
    private Long userID;
    private Long requestID;
    private boolean approved;

    public ResponseToRegistrationRequestDTO() { }

    public ResponseToRegistrationRequestDTO(ResponseToRegistrationRequest response) {
        this(response.getId(), response.getContent(), response.getAdministrator().getId(), response.getRegistrationRequest().getId(), response.isApproved());
    }
    public ResponseToRegistrationRequestDTO(Long id, String explanation, Long userID, Long requestID, boolean approved) {
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
