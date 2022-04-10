package com.isa.project.repository;

import com.isa.project.model.Client;
import com.isa.project.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    public List<Penalty> findByClient(Client client);
}
