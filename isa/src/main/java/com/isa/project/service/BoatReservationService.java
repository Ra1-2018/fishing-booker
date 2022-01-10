package com.isa.project.service;

import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.repository.BoatReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatReservationService {

    @Autowired
    BoatReservationRepository reservationRepository;

    public List<BoatReservation> findByClient(Client client) { return reservationRepository.findByClient(client); }
}
