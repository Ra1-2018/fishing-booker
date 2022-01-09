package com.isa.project.service;

import com.isa.project.model.Client;
import com.isa.project.model.CottageReservation;
import com.isa.project.repository.CottageReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageReservationService {

    @Autowired
    CottageReservationRepository reservationRepository;

    public List<CottageReservation> findByClient(Client client) { return  reservationRepository.findByClient(client); }
}
