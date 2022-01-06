package com.isa.project.repository;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage, Long> {

    @Query("select c from Cottage c where c.cottageOwner = ?1")
    public List<Cottage> findCottagesByOwner(CottageOwner cottageOwner);
}
