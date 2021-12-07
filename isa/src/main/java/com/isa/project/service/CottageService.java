package com.isa.project.service;

import com.isa.project.model.Cottage;
import com.isa.project.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CottageService{

    @Autowired
    private CottageRepository cottageRepository;

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public Cottage findById(Long id) {
        return cottageRepository.findById(id).orElseGet(null);
    }
}
