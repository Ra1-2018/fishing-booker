package com.isa.project.service;

import com.isa.project.model.ResponseToDeletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ResponseToDeletionTransactionService {

    @Autowired
    private ResponseToDeletionRequestService responseService;

    @Autowired
    private  DeletionRequestService deletionService;

    @Autowired
    private AppUserService appUserService;

    @Transactional
    public void responseToApproveDeletionTransactional(Long requestId, Long userId) throws OptimisticLockingFailureException {
            deletionService.remove(requestId);
            appUserService.remove(userId);
    }

    @Transactional
    public void responseToDeclineDeletionTransactional(ResponseToDeletionRequest response, Long requestId) throws OptimisticLockingFailureException{
            deletionService.remove(requestId);
            responseService.save(response);

    }
}
