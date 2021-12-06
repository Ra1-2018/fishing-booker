package com.isa.project.service;

import com.isa.project.model.Adventure;

import java.util.Collection;

public interface AdventureService {
    Collection<Adventure> findAll();

    Adventure findOne(long id);

    Adventure create(Adventure adventure) throws Exception;

    Adventure update(Adventure adventure) throws Exception;

    void delete(long id);
}
