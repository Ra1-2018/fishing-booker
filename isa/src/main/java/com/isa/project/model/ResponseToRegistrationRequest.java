package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ResponseToRegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;
    @OneToOne(mappedBy = "responseToRegistrationRequest")
    private RegistrationRequest registrationRequest;
    @Column
    private String content;
    @Column
    private boolean approved;

    public ResponseToRegistrationRequest() { }

    public ResponseToRegistrationRequest(Long id, Administrator administrator, RegistrationRequest registrationRequest, String content) {
        this.id = id;
        this.administrator = administrator;
        this.registrationRequest = registrationRequest;
        this.content = content;
        this.approved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public RegistrationRequest getRegistrationRequest() {
        return registrationRequest;
    }

    public void setRegistrationRequest(RegistrationRequest registrationRequest) {
        this.registrationRequest = registrationRequest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
