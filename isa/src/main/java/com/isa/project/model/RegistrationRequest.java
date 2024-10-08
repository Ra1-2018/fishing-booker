package com.isa.project.model;

import javax.persistence.*;

@Entity
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String explanation;
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responseToRegistrationRequest_id", referencedColumnName = "id")
    private ResponseToRegistrationRequest responseToRegistrationRequest;
    @Column
    private boolean approved;

    public RegistrationRequest(Long id, String explanation, AppUser user) {
        this.id = id;
        this.explanation = explanation;
        this.user = user;
        this.approved = false;
    }

    public RegistrationRequest() {
    }

    public String getExplanation() {
        return explanation;
    }

    public AppUser getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ResponseToRegistrationRequest getResponseToRegistrationRequest() {
        return responseToRegistrationRequest;
    }

    public void setResponseToRegistrationRequest(ResponseToRegistrationRequest responseToRegistrationRequest) {
        this.responseToRegistrationRequest = responseToRegistrationRequest;
    }
}
