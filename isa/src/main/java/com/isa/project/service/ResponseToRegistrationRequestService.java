package com.isa.project.service;

import com.isa.project.model.RegistrationRequest;
import com.isa.project.model.ResponseToRegistrationRequest;
import com.isa.project.repository.ResponseToRegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ResponseToRegistrationRequestService {

    @Autowired
    ResponseToRegistrationRequestRepository responseRequestRepository;

    public ResponseToRegistrationRequest save(ResponseToRegistrationRequest request) { return responseRequestRepository.save(request); }
    public ResponseToRegistrationRequest findById(Long id) {
        return responseRequestRepository.findById(id).orElse(null);
    }
}
