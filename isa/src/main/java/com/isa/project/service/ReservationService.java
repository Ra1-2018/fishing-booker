package com.isa.project.service;

import com.isa.project.model.Client;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Reservation;
import com.isa.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public List<Reservation> findByClient(Client client) { return reservationRepository.findByClient(client); }
    public List<Reservation> findByService(com.isa.project.model.Service service) { return  reservationRepository.findByService(service); }
    public Reservation save(Reservation reservation) { return reservationRepository.save(reservation); }
    public Reservation findById(Long id) { return reservationRepository.findById(id).orElse(null); }
    public void deleteById(Long id) { reservationRepository.deleteById(id); }
    public List<Reservation> findAll() { return reservationRepository.findAll(); }
}
