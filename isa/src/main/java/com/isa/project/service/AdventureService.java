package com.isa.project.service;

import com.isa.project.model.Adventure;
import com.isa.project.model.Instructor;
import com.isa.project.repository.AdventureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService{

    private final Logger LOG = LoggerFactory.getLogger(AdventureService.class);

    @Autowired
    private AdventureRepository adventureRepository;

    public List<Adventure> findAll() { return adventureRepository.findAll(); }

    public List<Adventure> findAdventureByInstructor(Instructor instructor) { return adventureRepository.findByInstructor(instructor); }

    @Cacheable("service")
    public Adventure findById(long id) {
        LOG.info("Adventure with id: " + id + " successfully cached!");
        return adventureRepository.findById(id).orElse(null);
    }
}
