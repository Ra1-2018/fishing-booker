package com.isa.project.service;

import com.isa.project.model.DeletionRequest;
import com.isa.project.repository.DeletionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletionRequestService {
    @Autowired
    private DeletionRequestRepository deletionRequestRepository;

    public DeletionRequest save(DeletionRequest deletionRequest) { return deletionRequestRepository.save(deletionRequest); }
}
