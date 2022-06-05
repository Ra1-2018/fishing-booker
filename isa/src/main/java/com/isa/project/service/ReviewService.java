package com.isa.project.service;

import com.isa.project.model.RegistrationRequest;
import com.isa.project.model.Review;
import com.isa.project.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) { return reviewRepository.save(review); }
    public Collection<Review> findAll() {
        return reviewRepository.findAll();
    }
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }
}
