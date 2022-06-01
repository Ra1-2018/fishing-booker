package com.isa.project.dto;

import com.isa.project.model.ResponseToComplaint;

import java.util.Date;

public class ResponseToComplaintDTO {
    private Long id;
    private Long userID;
    private String content;
    private Date dateSubmitted;
    private boolean approved;

    public ResponseToComplaintDTO() {}

    public ResponseToComplaintDTO(Long id, Long administrator, String content) {
        this.id = id;
        this.userID = administrator;
        this.content = content;
        this.approved = false;
    }

    public ResponseToComplaintDTO(ResponseToComplaint responseToComplaint) { this(responseToComplaint.getId(), responseToComplaint.getAdministrator().getId(), responseToComplaint.getContent());}

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
}
