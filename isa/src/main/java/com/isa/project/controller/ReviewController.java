package com.isa.project.controller;

import com.isa.project.dto.ReviewDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Review;
import com.isa.project.model.Service;
import com.isa.project.service.AppUserService;
import com.isa.project.service.ReviewService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> save(@RequestBody ReviewDTO reviewDTO) {
        Client client = (Client) appUserService.findOne(reviewDTO.getClient().getId());
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Service service = serviceService.findById(reviewDTO.getService().getId());
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Review review = new Review();
        review.setGrade(reviewDTO.getGrade());
        review.setRevision(reviewDTO.getRevision());
        review.setClient(client);
        review.setService(service);
        review.setApproved(false);
        review.setDateSubmitted(new Date());
        review = reviewService.save(review);
        return new ResponseEntity<>(new ReviewDTO(review), HttpStatus.OK);
    }
}
