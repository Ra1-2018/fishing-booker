package com.isa.project.service;

import com.isa.project.model.Boat;
import com.isa.project.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatService {
    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> findAll() { return boatRepository.findAll(); }

    public Boat findById(Long id) { return boatRepository.findById(id).orElse(null); }

    public Boat save(Boat boat) { return boatRepository.save(boat); }

    public void deleteById(Long id) { boatRepository.deleteById(id); }
}
