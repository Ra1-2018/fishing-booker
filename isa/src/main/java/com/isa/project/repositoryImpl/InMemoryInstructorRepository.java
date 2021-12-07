package com.isa.project.repositoryImpl;


import com.isa.project.model.Instructor;
import com.isa.project.model.OwnerType;
import com.isa.project.repository.InstructorRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryInstructorRepository implements InstructorRepository {

    private final ConcurrentHashMap<Long, Instructor> instructors = new ConcurrentHashMap<Long, Instructor>();

    public InMemoryInstructorRepository() {
        this.instructors.put(1L, new Instructor(1, "pera@gmail.com", "pera", "Petar", "Petrovic", "Blabla Bulevar 1","Zrenjanin", "Srbija", "023123456", OwnerType.INSTRUCTOR));
        this.instructors.put(2L, new Instructor(2, "mitar@gmail.com", "mitar", "Mitar", "Miric", "Blabla Bulevar 2","Zrenjanin", "Srbija", "023678901", OwnerType.INSTRUCTOR));
    }

    @Override
    public Collection<Instructor> findAll() {
        return this.instructors.values();
    }

    @Override
    public Instructor findOne(Long id) { return this.instructors.get(id); }

    @Override
    public void delete(Long id) { instructors.remove(id); }
}
