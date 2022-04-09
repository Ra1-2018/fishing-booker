package com.isa.project.controller;

import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.dto.ServiceDTO;
import com.isa.project.model.*;
import com.isa.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
    public ResponseEntity<Collection<ServiceDTO>> getServicesForCriteria(@RequestBody ServiceCriteriaDTO serviceCriteria) {
        Collection<Service> services = serviceService.getIfMatchesCriteria(serviceCriteria);
        Collection<ServiceDTO> serviceDTOS = new ArrayList<>();
        for(Service service : services) {
            serviceDTOS.add(new ServiceDTO(service));
        }
        return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "subscribe/{serviceId}/{clientId}")
    public ResponseEntity<Void> subscribeToService(@PathVariable long serviceId, @PathVariable long clientId) {
        Service service = serviceService.findById(serviceId);
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Client client = (Client) appUserService.findOne(clientId);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        client.addSubscription(service);
        appUserService.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "subscriptions/{id}")
    public ResponseEntity<Collection<ServiceDTO>> getSubscriptions(@PathVariable long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Collection<ServiceDTO> serviceDTOS = new ArrayList<>();
        for(Service service : client.getSubscriptions()) {
            serviceDTOS.add(new ServiceDTO(service));
        }
        return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "unsubscribe/{serviceId}/{clientId}")
    public ResponseEntity<Void> unsubscribeFromService(@PathVariable long serviceId, @PathVariable long clientId) {
        Service service = serviceService.findById(serviceId);
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Client client = (Client) appUserService.findOne(clientId);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        client.removeSubscription(service);
        appUserService.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "eligible/{id}")
    public ResponseEntity<Collection<ServiceDTO>> getServicesFromReservations(@PathVariable long id) {
        Client client = (Client) appUserService.findOne(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Collection<ServiceDTO> serviceDTOS = new ArrayList<>();
        for(Service service : serviceService.getServicesFromReservations(client)) {
            serviceDTOS.add(new ServiceDTO(service));
        }
        return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
    }
}
