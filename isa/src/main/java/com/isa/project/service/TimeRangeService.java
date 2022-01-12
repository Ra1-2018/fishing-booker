package com.isa.project.service;

import com.isa.project.model.TimeRange;
import com.isa.project.repository.TimeRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeRangeService {
    @Autowired
    private TimeRangeRepository timeRangeRepository;

    public void deleteById(Long id) { timeRangeRepository.deleteById(id); }

    public TimeRange save(TimeRange timeRange) {return timeRangeRepository.save(timeRange); }
}
