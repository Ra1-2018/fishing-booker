package com.isa.project.repository;

import com.isa.project.model.DeletionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletionRequestRepository extends JpaRepository<DeletionRequest, Long> {
}
