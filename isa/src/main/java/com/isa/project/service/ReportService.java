package com.isa.project.service;

import com.isa.project.model.Report;
import com.isa.project.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report save(Report report) { return  reportRepository.save(report); }
}
