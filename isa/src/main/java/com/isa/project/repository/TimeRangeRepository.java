package com.isa.project.repository;

import com.isa.project.model.Service;
import com.isa.project.model.TimeRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TimeRangeRepository extends JpaRepository<TimeRange, Long> {
    List<TimeRange> findByService(Service service);
}
