package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ResponseToComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;
    @OneToOne(mappedBy = "responseToComplaint")
    private Complaint complaint;
    @Column
    private String content;
    @Column
    private Date dateSubmitted;

    @Column
    private boolean approved;

    public ResponseToComplaint() {}

    public ResponseToComplaint(Long id, Administrator administrator, Complaint complaint, String content) {
        this.id = id;
        this.administrator = administrator;
        this.complaint = complaint;
        this.content = content;
        this.approved = false;
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

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
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
