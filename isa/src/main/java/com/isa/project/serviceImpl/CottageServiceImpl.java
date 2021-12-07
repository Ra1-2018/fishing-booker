package com.isa.project.serviceImpl;

import com.isa.project.model.Cottage;
import com.isa.project.repositoryImpl.InMemoryCottageRepository;
import com.isa.project.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CottageServiceImpl implements CottageService {

    @Autowired
    private InMemoryCottageRepository cottageRepository;

    @Override
    public Collection<Cottage> findAll() {
        Collection<Cottage> cottages = cottageRepository.findAll();
        return cottages;
    }

    @Override
    public Cottage findOne(Long id) {
        Cottage cottage = cottageRepository.findOne(id);
        return cottage;
    }

    @Override
    public void delete(Long id) {
        cottageRepository.delete(id);
    }
}
