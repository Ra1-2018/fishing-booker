package com.isa.project.service;

import com.isa.project.model.Adventure;
import com.isa.project.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService{

    @Autowired
    private AdventureRepository adventureRepository;

    public List<Adventure> findAll() { return adventureRepository.findAll(); }
    public Adventure findById(long id) {
        return adventureRepository.findById(id).orElseGet(null);
    }
}
