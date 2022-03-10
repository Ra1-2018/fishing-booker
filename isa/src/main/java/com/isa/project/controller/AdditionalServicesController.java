package com.isa.project.controller;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.model.AdditionalService;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.ServiceType;
import com.isa.project.service.AdditionalServiceService;
import com.isa.project.service.CottageService;
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
    private CottageService cottageService;

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdditionalServiceDTO> createAdditionalService(@RequestBody AdditionalServiceDTO additionalServiceDTO) {


        Cottage cottage = cottageService.findById(additionalServiceDTO.getServiceId());

        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AdditionalService additionalService = new AdditionalService();

        additionalService.setName(additionalServiceDTO.getName());
        additionalService.setPrice(additionalServiceDTO.getPrice());
        additionalService.setService(cottage);

        additionalServiceService.save(additionalService);
        cottage.addAdditionalService(additionalService);
        cottageService.save(cottage);

        return new ResponseEntity<>(new AdditionalServiceDTO(additionalService), HttpStatus.CREATED);
    }
}
