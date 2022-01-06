package com.isa.project.service;

import com.isa.project.model.Instructor;
import com.isa.project.model.RegistrationRequest;
import com.isa.project.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RegistrationRequestService {

    @Autowired
    RegistrationRequestRepository requestRepository;

    public RegistrationRequest save(RegistrationRequest request) { return requestRepository.save(request); }

    public Collection<RegistrationRequest> findAll() {
        return requestRepository.findAll();
    }

    public RegistrationRequest findById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }
}
