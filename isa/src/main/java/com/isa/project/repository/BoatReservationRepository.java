package com.isa.project.repository;

import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long> {
    public List<BoatReservation> findByClient(Client client);
}
