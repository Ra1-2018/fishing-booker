package com.isa.project.service;

import com.isa.project.model.ResponseToComplaint;
import com.isa.project.model.ResponseToDeletionRequest;
import com.isa.project.repository.ResponseToComplaintRepository;
import com.isa.project.repository.ResponseToDeletionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseToComplaintService {

    @Autowired
    ResponseToComplaintRepository responseToComplaintRepository;

    public ResponseToComplaint save(ResponseToComplaint request) { return responseToComplaintRepository.save(request); }
    public ResponseToComplaint findById(Long id) {
        return responseToComplaintRepository.findById(id).orElse(null);
    }
    public void remove(long id) {
        responseToComplaintRepository.deleteById(id);
    }
}
