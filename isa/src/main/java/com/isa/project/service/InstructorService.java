package com.isa.project.service;

import com.isa.project.model.Instructor;

import java.util.Collection;

public interface InstructorService {

    Collection<Instructor> findAll();

    Instructor findOne(Long id);

    void delete(Long id);
}
