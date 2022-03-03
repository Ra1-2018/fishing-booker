package com.isa.project.controller;

import com.isa.project.dto.ComplaintDTO;
import com.isa.project.dto.ReviewDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Complaint;
import com.isa.project.model.Service;
import com.isa.project.service.AppUserService;
import com.isa.project.service.ComplaintService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintDTO> save(@RequestBody ComplaintDTO complaintDTO) {
        Client client = (Client) appUserService.findOne(complaintDTO.getClient().getId());
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Service service = serviceService.findById(complaintDTO.getService().getId());
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Service> services = serviceService.getServicesFromReservations(client);
        boolean valid = false;
        for(Service s : services) {
            if(s.getId() == service.getId()) {
                valid = true;
                break;
            }
        }
        if(!valid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Complaint complaint = new Complaint();
        complaint.setContent(complaintDTO.getContent());
        complaint.setClient(client);
        complaint.setService(service);
        complaint = complaintService.save(complaint);
        return new ResponseEntity<>(new ComplaintDTO(complaint), HttpStatus.OK);
    }
}
