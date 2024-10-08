package com.isa.project.dto;

import com.isa.project.model.Client;
import com.isa.project.model.Complaint;
import com.isa.project.model.Service;

import java.util.Date;

public class ComplaintDTO {
    private long id;
    private AppUserDTO client;
    private ServiceDTO service;
    private String content;
    private Date dateSubmitted;

    private boolean approved;

    public ComplaintDTO() {}

    public ComplaintDTO(long id, AppUserDTO client, ServiceDTO service, String content, Date dateSubmitted, boolean approved) {
        this.id = id;
        this.client = client;
        this.service = service;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.approved = approved;
    }

    public ComplaintDTO(Complaint complaint) {this(complaint.getId(), new AppUserDTO(complaint.getClient()), new ServiceDTO(complaint.getService()), complaint.getContent(), complaint.getDateSubmitted(), complaint.isApproved()); }

    public long getId() {
        return id;
    }

    public AppUserDTO getClient() {
        return client;
    }

    public ServiceDTO getService() {
        return service;
    }

    public String getContent() {
        return content;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }
}
