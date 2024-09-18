package com.isa.project.service;

import com.isa.project.model.Complaint;
import com.isa.project.model.CottageOwner;
import com.isa.project.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> findAll() { return complaintRepository.findAll(); }
    public Complaint save(Complaint complaint) { return complaintRepository.save(complaint); }

    public Complaint findById(long id) {
        return complaintRepository.findById(id).orElse(null);
    }
    public void remove(long id) {
        complaintRepository.deleteById(id);
    }
}
