package com.isa.project.controller;

import com.isa.project.dto.DeletionRequestDTO;
import com.isa.project.model.DeletionRequest;
import com.isa.project.service.DeletionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deletionRequests")
public class DeletionRequestController {
    @Autowired
    private DeletionRequestService deletionRequestService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeletionRequestDTO> save(@RequestBody DeletionRequestDTO deletionRequestDTO) {
        DeletionRequest deletionRequest = new DeletionRequest();
        deletionRequest.setExplanation(deletionRequestDTO.getExplanation());
        deletionRequest.setUserEmail(deletionRequestDTO.getUserEmail());
        deletionRequest = deletionRequestService.save(deletionRequest);
        return new ResponseEntity<>(new DeletionRequestDTO(deletionRequest), HttpStatus.OK);
    }
}
