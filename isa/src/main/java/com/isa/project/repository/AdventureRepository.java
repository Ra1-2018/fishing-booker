package com.isa.project.repository;

import com.isa.project.model.Adventure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}
