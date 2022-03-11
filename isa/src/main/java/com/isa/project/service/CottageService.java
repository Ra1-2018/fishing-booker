package com.isa.project.service;

import com.isa.project.model.AppUser;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.TimeRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CottageService{

    @Autowired
    private CottageRepository cottageRepository;
    private TimeRangeRepository timeRangeRepository;

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public List<Cottage> findCottagesByOwner(CottageOwner cottageOwner) { return cottageRepository.findCottagesByOwner(cottageOwner); }

    public Cottage findById(Long id) {
        return cottageRepository.findById(id).orElse(null);
    }

    public Cottage save(Cottage cottage) {
        cottage.setLastUpdateDate(new Date());
        return cottageRepository.save(cottage);
    }

    public void remove(long id) {
        cottageRepository.deleteById(id);
    }
}
