package com.isa.project.serviceImpl;

import com.isa.project.model.Instructor;
import com.isa.project.repositoryImpl.InMemoryInstructorRepository;
import com.isa.project.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InstructorServiceImpl  implements InstructorService {

    @Autowired
    private InMemoryInstructorRepository instructorRepository;
    @Override
    public Collection<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findOne(Long id) {
        return instructorRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        instructorRepository.delete(id);
    }
}
