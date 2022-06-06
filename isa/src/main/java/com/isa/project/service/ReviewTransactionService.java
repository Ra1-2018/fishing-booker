package com.isa.project.service;

import com.isa.project.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewTransactionService {

    @Autowired
    private ReviewService reviewService;

    @Transactional
    public void responseToApproveReviewTransactional(Review review) throws OptimisticLockingFailureException {
        reviewService.save(review);
    }

    @Transactional
    public void responseToDeclineReviewTransactional(Long reviewId) throws OptimisticLockingFailureException{
        reviewService.remove(reviewId);
    }
}
