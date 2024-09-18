package com.isa.project.service;

import com.isa.project.model.RegistrationRequest;
import com.isa.project.model.Report;
import com.isa.project.model.Reservation;
import com.isa.project.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report save(Report report) { return  reportRepository.save(report); }
    public List<Report> findAll() { return reportRepository.findAll(); }
    public Report findById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }
    public void remove(Long id) {
        reportRepository.deleteById(id);
    }

}
