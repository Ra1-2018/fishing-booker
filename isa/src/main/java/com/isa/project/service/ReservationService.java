package com.isa.project.service;

import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import com.isa.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    public List<Reservation> findByClient(Client client) { return reservationRepository.findByClient(client); }
}
