package com.isa.project.dto;

import com.isa.project.model.ResponseToComplaint;

public class ResponseToComplaintDTO {
    private long id;
    private AppUserDTO administrator;
    private String content;

    public ResponseToComplaintDTO() {}

    public ResponseToComplaintDTO(long id, AppUserDTO administrator, String content) {
        this.id = id;
        this.administrator = administrator;
        this.content = content;
    }

    public ResponseToComplaintDTO(ResponseToComplaint responseToComplaint) { this(responseToComplaint.getId(), new AppUserDTO(responseToComplaint.getAdministrator()), responseToComplaint.getContent());}

    public long getId() {
        return id;
    }

    public AppUserDTO getAdministrator() {
        return administrator;
    }

    public String getContent() {
        return content;
    }
}
