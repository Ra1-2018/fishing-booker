package com.isa.project.repository;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Query("select b from Boat b where b.boatOwner = ?1")
    public List<Boat> findBoatsByOwner(BoatOwner boatOwner);
}
