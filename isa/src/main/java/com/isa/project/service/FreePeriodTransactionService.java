package com.isa.project.service;

import com.isa.project.model.TimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class FreePeriodTransactionService {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private TimeRangeService timeRangeService;

    @Transactional(readOnly = false)
    public void removeFreePeriod(TimeRange freePeriod) {
        serviceService.RemoveFreePeriodTimeRange(freePeriod);
        timeRangeService.deleteById(freePeriod.getId());
    }
}
