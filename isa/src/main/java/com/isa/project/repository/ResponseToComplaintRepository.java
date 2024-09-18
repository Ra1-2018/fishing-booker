package com.isa.project.repository;

import com.isa.project.model.ResponseToComplaint;
import com.isa.project.model.ResponseToDeletionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseToComplaintRepository extends JpaRepository<ResponseToComplaint, Long> {
}
