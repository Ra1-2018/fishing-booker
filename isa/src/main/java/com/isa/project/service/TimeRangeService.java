package com.isa.project.service;

import com.isa.project.model.Cottage;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.TimeRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class TimeRangeService {

    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private TimeRangeRepository timeRangeRepository;

    public List<TimeRange> findAll() {
        return timeRangeRepository.findAll();
    }

    //public List<TimeRange> findTimeRangeByService(Service service) { return timeRangeRepository.findTimeRangeByService(service); }

    public TimeRange findById(Long id) {
        return timeRangeRepository.findById(id).orElse(null);
    }

    public TimeRange save(TimeRange timeRange) {
        return timeRangeRepository.save(timeRange);
    }

    public void deleteById(long id) {
        timeRangeRepository.deleteById(id);
    }
}
