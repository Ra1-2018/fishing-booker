package com.isa.project.model;

import javax.persistence.*;

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

    public DeletionRequest() {}

    public DeletionRequest(long id, String userEmail, String explanation, ResponseToDeletionRequest responseToDeletionRequest) {
        this.id = id;
        this.userEmail = userEmail;
        this.explanation = explanation;
        this.responseToDeletionRequest = responseToDeletionRequest;
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
}
