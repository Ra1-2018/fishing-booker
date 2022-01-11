package com.isa.project.service;

import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageOwnerService {

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    public List<CottageOwner> findAll() { return cottageOwnerRepository.findAll(); }

    public CottageOwner findById(long id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public CottageOwner findOneWithCottages(long cottageOwnerId) { return cottageOwnerRepository.findOneWithCottages(cottageOwnerId); }

    public CottageOwner save(CottageOwner cottageOwner) {
        return cottageOwnerRepository.save(cottageOwner);
    }
}
