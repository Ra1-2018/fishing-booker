package com.isa.project.service;

import com.isa.project.model.Review;
import com.isa.project.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) { return reviewRepository.save(review); }
}
