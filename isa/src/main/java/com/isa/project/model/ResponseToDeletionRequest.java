package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ResponseToDeletionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Version
    private Integer version;

    public ResponseToDeletionRequest() {}

    public ResponseToDeletionRequest(Long id, Administrator administrator, DeletionRequest deletionRequest, String content) {
        this.id = id;
        this.administrator = administrator;
        this.deletionRequest = deletionRequest;
        this.content = content;
        this.approved = false;
        this.dateSubmitted = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
