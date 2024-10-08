package com.isa.project.service;

import com.isa.project.model.Adventure;
import com.isa.project.model.Instructor;
import com.isa.project.repository.AdventureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Adventure> findAdventuresByOwner(Instructor instructor) { return adventureRepository.findAdventuresByOwner(instructor); }

    @CachePut(value = "service", key = "#adventure.id")
    public Adventure save(Adventure adventure) {
        adventure.setLastUpdateDate(new Date());
        return adventureRepository.save(adventure);
    }

    @CacheEvict(cacheNames = {"service"}, key = "#id")
    public void remove(long id) {
        adventureRepository.deleteById(id);
    }
}
