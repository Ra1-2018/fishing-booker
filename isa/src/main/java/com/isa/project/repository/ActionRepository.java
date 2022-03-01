package com.isa.project.repository;

import com.isa.project.model.Action;
import com.isa.project.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    public List<Action> findByService(Service service);
}
