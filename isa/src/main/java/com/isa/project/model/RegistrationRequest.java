package com.isa.project.model;

import javax.persistence.*;

@Entity
public class RegistrationRequest {

    @Column
    private String explanation;
    @Id
    private Long id;
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser user;
    @Column
    private boolean approved;

    public RegistrationRequest(String explanation, AppUser user) {
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
}
