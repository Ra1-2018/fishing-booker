package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ResponseToDeletionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;
    @OneToOne(mappedBy = "responseToDeletionRequest")
    private DeletionRequest deletionRequest;
    @Column
    private String content;
    @Column
    private boolean approved;
    @Column
    private Date dateSubmitted;

    public ResponseToDeletionRequest() {}

    public ResponseToDeletionRequest(long id, Administrator administrator, DeletionRequest deletionRequest, String content, boolean approved, Date dateSubmitted) {
        this.id = id;
        this.administrator = administrator;
        this.deletionRequest = deletionRequest;
        this.content = content;
        this.approved = approved;
        this.dateSubmitted = dateSubmitted;
    }

    public long getId() {
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

    public DeletionRequest getDeletionRequest() {
        return deletionRequest;
    }

    public void setDeletionRequest(DeletionRequest deletionRequest) {
        this.deletionRequest = deletionRequest;
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

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
