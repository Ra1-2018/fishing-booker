package com.isa.project.repository;

import com.isa.project.model.Cottage;

import java.util.Collection;

public interface CottageRepository {

    Collection<Cottage> findAll();

    Cottage findOne(Long id);

    void delete(Long id);
}
