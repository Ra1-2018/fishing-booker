package com.isa.project.service;

import com.isa.project.model.Complaint;
import com.isa.project.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint save(Complaint complaint) { return complaintRepository.save(complaint); }
}
