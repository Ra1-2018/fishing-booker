package com.isa.project.service;

import com.isa.project.model.BoatOwner;
import com.isa.project.model.CottageOwner;
import com.isa.project.repository.BoatOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatOwnerService {

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    public BoatOwner save(BoatOwner boatOwner) {
        return boatOwnerRepository.save(boatOwner);
    }

    public List<BoatOwner> findAll() { return boatOwnerRepository.findAll(); }

    public BoatOwner findById(long id) {
        return boatOwnerRepository.findById(id).orElseGet(null);
    }
}
