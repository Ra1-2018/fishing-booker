package com.isa.project.service;

import com.isa.project.model.ResponseToDeletionRequest;
import com.isa.project.model.ResponseToRegistrationRequest;
import com.isa.project.repository.ResponseToDeletionRequestRepository;
import com.isa.project.repository.ResponseToRegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResponseToDeletionRequestService {

    @Autowired
    ResponseToDeletionRequestRepository responseDeletionRequestRepository;

    public ResponseToDeletionRequest save(ResponseToDeletionRequest request) {
        request.setDateSubmitted(new Date());
        return responseDeletionRequestRepository.save(request);
    }
    public ResponseToDeletionRequest findById(Long id) {
        return responseDeletionRequestRepository.findById(id).orElse(null);
    }
    public void remove(long id) {
        responseDeletionRequestRepository.deleteById(id);
    }
}
