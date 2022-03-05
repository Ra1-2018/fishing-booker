package com.isa.project.dto;

import com.isa.project.model.ResponseToComplaint;

import java.util.Date;

public class ResponseToComplaintDTO {
    private long id;
    private AppUserDTO administrator;
    private String content;
    private Date dateSubmitted;

    public ResponseToComplaintDTO() {}

    public ResponseToComplaintDTO(long id, AppUserDTO administrator, String content, Date dateSubmitted) {
        this.id = id;
        this.administrator = administrator;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
    }

    public ResponseToComplaintDTO(ResponseToComplaint responseToComplaint) { this(responseToComplaint.getId(), new AppUserDTO(responseToComplaint.getAdministrator()), responseToComplaint.getContent(), responseToComplaint.getDateSubmitted());}

    public long getId() {
        return id;
    }

    public AppUserDTO getAdministrator() {
        return administrator;
    }

    public String getContent() {
        return content;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }
}
