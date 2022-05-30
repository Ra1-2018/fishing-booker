package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeletionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userEmail;
    @Column
    private String explanation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responseToDeletionRequest_id", referencedColumnName = "id")
    private ResponseToDeletionRequest responseToDeletionRequest;

    @Column
    private Date dateSubmitted;

    public DeletionRequest() {}

    public DeletionRequest(long id, String userEmail, String explanation, ResponseToDeletionRequest responseToDeletionRequest, Date dateSubmitted) {
        this.id = id;
        this.userEmail = userEmail;
        this.explanation = explanation;
        this.responseToDeletionRequest = responseToDeletionRequest;
        this.dateSubmitted = dateSubmitted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
