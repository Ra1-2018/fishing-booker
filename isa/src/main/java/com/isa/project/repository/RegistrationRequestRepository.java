package com.isa.project.repository;

import com.isa.project.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    @Query("select r from RegistrationRequest r where r.user.id = ?1")
    public RegistrationRequest findByUserId(Long id);
}
