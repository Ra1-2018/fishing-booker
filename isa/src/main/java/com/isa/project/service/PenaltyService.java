package com.isa.project.service;

import com.isa.project.model.Client;
import com.isa.project.model.Penalty;
import com.isa.project.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;

    public List<Penalty> findByClient(Client client) { return penaltyRepository.findByClient(client); }
}
