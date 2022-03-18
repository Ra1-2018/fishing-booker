package com.isa.project.repository;

import com.isa.project.model.Adventure;
import com.isa.project.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    @Query("select a from Adventure a where a.instructor = ?1")
    public List<Adventure> findAdventuresByOwner(Instructor instructor);
}
