package com.isa.project.controller;

import com.isa.project.dto.BusinessReportDTO;
import com.isa.project.model.AppUser;
import com.isa.project.model.Service;
import com.isa.project.service.AppUserService;
import com.isa.project.service.BusinessReportService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessReports")
public class BusinessReportController {

    @Autowired
    private BusinessReportService businessReportService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BusinessReportDTO> getBusinessReport(@PathVariable("id") Long id) {
        BusinessReportDTO businessReportDTO = businessReportService.getBusinessReport(id);
        return new ResponseEntity<>(businessReportDTO, HttpStatus.OK);
    }
}
