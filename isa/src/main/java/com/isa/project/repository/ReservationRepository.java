package com.isa.project.repository;

import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import com.isa.project.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByClient(Client client);
    public List<Reservation> findByService(Service service);
}
