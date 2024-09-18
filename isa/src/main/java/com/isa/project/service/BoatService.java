package com.isa.project.service;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatOwner;
import com.isa.project.repository.BoatRepository;
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
public class BoatService {

    private final Logger LOG = LoggerFactory.getLogger(BoatService.class);

    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> findAll() { return boatRepository.findAll(); }

    @Cacheable("service")
    public Boat findById(Long id) {
        LOG.info("Boat with id: " + id + " successfully cached!");
        return boatRepository.findById(id).orElse(null);
    }

    public List<Boat> findBoatsByOwner(BoatOwner boatOwner) { return boatRepository.findBoatsByOwner(boatOwner); }

    @CachePut(value = "service", key = "#boat.id")
    public Boat save(Boat boat) {
        boat.setLastUpdateDate(new Date());
        return boatRepository.save(boat);
    }

    @CacheEvict(cacheNames = {"service"}, key = "#id")
    public void deleteById(Long id) { boatRepository.deleteById(id); }
}
