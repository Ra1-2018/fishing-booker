package com.isa.project.repository;

import com.isa.project.model.Cottage;
import com.isa.project.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    public List<Room> findByCottage(Cottage cottage);
}
