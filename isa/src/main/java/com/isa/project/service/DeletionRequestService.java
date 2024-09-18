package com.isa.project.service;

import com.isa.project.model.DeletionRequest;
import com.isa.project.model.Instructor;
import com.isa.project.model.RegistrationRequest;
import com.isa.project.repository.DeletionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DeletionRequestService {
    @Autowired
    private DeletionRequestRepository deletionRequestRepository;

    public DeletionRequest save(DeletionRequest deletionRequest) { return deletionRequestRepository.save(deletionRequest); }
    public DeletionRequest findById(Long id) {
        return deletionRequestRepository.findById(id).orElse(null);
    }
    public void remove(long id) {
        deletionRequestRepository.deleteById(id);
    }

    public Collection<DeletionRequest> findAll() {
        return deletionRequestRepository.findAll();
    }
}
