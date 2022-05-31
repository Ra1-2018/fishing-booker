package com.isa.project.service;

import com.isa.project.model.Action;
import com.isa.project.model.Cottage;
import com.isa.project.model.Room;
import com.isa.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() { return roomRepository.findAll(); }

    public Room findById(Long id) { return  roomRepository.findById(id).orElse(null); }

    public void deleteById(Long id) { roomRepository.deleteById(id);}

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> findByCottage(Cottage cottage) { return roomRepository.findByCottage(cottage); }

}
