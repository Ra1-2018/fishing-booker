package com.isa.project.service;

import com.isa.project.model.Instructor;
import com.isa.project.repository.InstructorRepository;
import com.isa.project.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;

    public Collection<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }
}
