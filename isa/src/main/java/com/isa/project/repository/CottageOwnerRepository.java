package com.isa.project.repository;

import com.isa.project.model.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Long> {

    @Query("select c from CottageOwner c join fetch c.cottages ct where c.id =?1")
    public CottageOwner findOneWithCottages(long cottageOwnerId);
}
