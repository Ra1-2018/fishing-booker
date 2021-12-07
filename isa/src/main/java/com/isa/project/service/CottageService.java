package com.isa.project.service;

import com.isa.project.model.Cottage;

import java.util.Collection;

public interface CottageService {

    Collection<Cottage> findAll();

    Cottage findOne(Long id);

    void delete(Long id);
}
