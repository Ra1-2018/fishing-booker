package com.isa.project.repository;

import com.isa.project.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Long>{

    @Query("select b from BoatOwner b join fetch b.boats bt where b.id =?1")
    public BoatOwner findOneWithBoats(long boatOwnerId);
}
