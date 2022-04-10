package com.isa.project.controller;

import com.isa.project.dto.PenaltyDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Penalty;
import com.isa.project.service.AppUserService;
import com.isa.project.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/penalties")
public class PenaltyController {
    @Autowired
    private PenaltyService penaltyService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PenaltyDTO>> findByClient(@PathVariable Long id) {
        Client client = (Client) appUserService.findOne(id);

        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Penalty> penalties = penaltyService.findByClient(client);
        Collection<PenaltyDTO> penaltyDTOS = new HashSet<>();
        for(Penalty penalty : penalties) {
            penaltyDTOS.add(new PenaltyDTO(penalty));
        }
        return new ResponseEntity<>(penaltyDTOS, HttpStatus.OK);
    }
}
