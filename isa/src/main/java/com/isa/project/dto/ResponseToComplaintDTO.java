package com.isa.project.dto;

import com.isa.project.model.ResponseToComplaint;
import com.isa.project.model.ResponseToDeletionRequest;

import java.util.Date;

public class ResponseToComplaintDTO {
    private Long id;
    private Long userID;

    private Long requestID;
    private String content;
    private Date dateSubmitted;
    private boolean approved;

    public ResponseToComplaintDTO() {}

    public ResponseToComplaintDTO(ResponseToComplaint response) {
        this(response.getId(), response.getContent(), response.getAdministrator().getId(), response.getComplaint().getId(), response.isApproved());
    }

    public ResponseToComplaintDTO(Long id, String explanation, Long userID, Long requestID, boolean approved) {
        this.id = id;
        this.content = explanation;
        this.userID = userID;
        this.requestID = requestID;
        this.approved = approved;
    }
    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public boolean isApproved() {
        return approved;
    }

    public Long getRequestID() {
        return requestID;
    }
}
