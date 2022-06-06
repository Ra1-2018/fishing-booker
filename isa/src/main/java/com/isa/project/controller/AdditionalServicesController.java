package com.isa.project.controller;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.model.*;
import com.isa.project.service.AdditionalServiceService;
import com.isa.project.service.CottageService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
@RequestMapping("/additionalServices")
public class AdditionalServicesController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @PreAuthorize("hasRole('COTTAGE_OWNER') || hasRole('INSTRUCTOR') || hasRole('BOAT_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdditionalServiceDTO> createAdditionalService(@RequestBody AdditionalServiceDTO additionalServiceDTO) {


        Service service = serviceService.findById(additionalServiceDTO.getServiceId());

        if (service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AdditionalService additionalService = new AdditionalService();

        additionalService.setName(additionalServiceDTO.getName());
        additionalService.setPrice(additionalServiceDTO.getPrice());
        additionalService.setService(service);

        additionalServiceService.save(additionalService);
        service.addAdditionalService(additionalService);
        serviceService.save(service);

        return new ResponseEntity<>(new AdditionalServiceDTO(additionalService), HttpStatus.CREATED);
    }
}
