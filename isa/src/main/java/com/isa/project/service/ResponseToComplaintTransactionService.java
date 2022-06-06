package com.isa.project.service;

import com.isa.project.model.Complaint;
import com.isa.project.model.ResponseToComplaint;
import com.isa.project.model.ResponseToDeletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ResponseToComplaintTransactionService {

    @Autowired
    private ResponseToComplaintService responseService;

    @Autowired
    private  ComplaintService complaintService;


    @Transactional
    public void responseToApproveComplaintTransactional(Complaint complaint) throws OptimisticLockingFailureException {
        complaintService.save(complaint);
    }

    @Transactional
    public void responseToDeclineComplaintTransactional(ResponseToComplaint response, Long complaintId) throws OptimisticLockingFailureException{
        complaintService.remove(complaintId);
        responseService.save(response);
    }
}
