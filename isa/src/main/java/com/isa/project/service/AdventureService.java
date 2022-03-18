package com.isa.project.service;

import com.isa.project.model.Adventure;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Instructor;
import com.isa.project.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdventureService{

    @Autowired
    private AdventureRepository adventureRepository;

    public List<Adventure> findAll() { return adventureRepository.findAll(); }
    public Adventure findById(long id) {
        return adventureRepository.findById(id).orElse(null);
    }
    public List<Adventure> findAdventuresByOwner(Instructor instructor) { return adventureRepository.findAdventuresByOwner(instructor); }
    public Adventure save(Adventure adventure) {
        adventure.setLastUpdateDate(new Date());
        return adventureRepository.save(adventure);
    }
    public void remove(long id) {
        adventureRepository.deleteById(id);
    }
}
