package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;
    @Column
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responseToComplaint_id", referencedColumnName = "id")
    private ResponseToComplaint responseToComplaint;
    @Column
    private Date dateSubmitted;

    public Complaint() {}

    public Complaint(long id, Client client, Service service, String content, ResponseToComplaint responseToComplaint, Date dateSubmitted) {
        this.id = id;
        this.client = client;
        this.service = service;
        this.content = content;
        this.responseToComplaint = responseToComplaint;
        this.dateSubmitted = dateSubmitted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResponseToComplaint getResponseToComplaint() {
        return responseToComplaint;
    }

    public void setResponseToComplaint(ResponseToComplaint responseToComplaint) {
        this.responseToComplaint = responseToComplaint;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
