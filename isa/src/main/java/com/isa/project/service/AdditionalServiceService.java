package com.isa.project.service;

import com.isa.project.model.AdditionalService;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.AdditionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceService {
    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    public AdditionalService findById(Long id) { return additionalServiceRepository.findById(id).orElse(null); }

    public AdditionalService save(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }
}
