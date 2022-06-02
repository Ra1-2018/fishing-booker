package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeletionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userEmail;
    @Column
    private String explanation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responseToDeletionRequest_id", referencedColumnName = "id")
    private ResponseToDeletionRequest responseToDeletionRequest;

    @Column
    private boolean approved;

    @Column
    private Date dateSubmitted;

    public DeletionRequest() {}

    public DeletionRequest(Long id, String userEmail, String explanation, ResponseToDeletionRequest responseToDeletionRequest, Date dateSubmitted) {
        this.id = id;
        this.userEmail = userEmail;
        this.explanation = explanation;
        this.responseToDeletionRequest = responseToDeletionRequest;
        this.dateSubmitted = dateSubmitted;
        this.approved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public ResponseToDeletionRequest getResponseToDeletionRequest() {
        return responseToDeletionRequest;
    }

    public void setResponseToDeletionRequest(ResponseToDeletionRequest responseToDeletionRequest) {
        this.responseToDeletionRequest = responseToDeletionRequest;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
