package com.isa.project.repository;

import com.isa.project.model.Client;
import com.isa.project.model.CottageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CottageReservationRepository extends JpaRepository<CottageReservation, Long> {
    public List<CottageReservation> findByClient(Client client);
}
