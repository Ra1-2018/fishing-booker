package com.isa.project.repository;

import com.isa.project.model.Adventure;

import java.util.Collection;

public interface AdventureRepository {
    Collection<Adventure> findAll();

    Adventure create(Adventure adventure);

    Adventure findOne(long id);

    Adventure update(Adventure adventure);

    void delete(long id);
}
