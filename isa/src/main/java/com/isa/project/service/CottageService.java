package com.isa.project.service;

import com.isa.project.model.AppUser;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.TimeRangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CottageService{

    private final Logger LOG = LoggerFactory.getLogger(CottageService.class);

    @Autowired
    private CottageRepository cottageRepository;
    private TimeRangeRepository timeRangeRepository;

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public List<Cottage> findCottagesByOwner(CottageOwner cottageOwner) { return cottageRepository.findCottagesByOwner(cottageOwner); }

    @Cacheable("service")
    public Cottage findById(Long id) {
        LOG.info("Cottage with id: " + id + " successfully cached!");
        return cottageRepository.findById(id).orElse(null);
    }

    @CachePut(value = "service", key = "#cottage.id")
    public Cottage save(Cottage cottage) {
        cottage.setLastUpdateDate(new Date());
        return cottageRepository.save(cottage);
    }

    @CacheEvict(cacheNames = {"service"}, key = "#id")
    public void remove(long id) {
        cottageRepository.deleteById(id);
    }
}
