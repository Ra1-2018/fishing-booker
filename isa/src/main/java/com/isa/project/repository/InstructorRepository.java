package com.isa.project.repository;

import com.isa.project.model.Instructor;

import java.util.Collection;

public interface InstructorRepository {

    Collection<Instructor> findAll();

    Instructor findOne(Long id);

    void delete(Long id);
}
