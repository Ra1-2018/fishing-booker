package com.isa.project.repository;

import com.isa.project.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
