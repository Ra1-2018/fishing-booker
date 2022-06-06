package com.isa.project.service;

import com.isa.project.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResponseToComplaintTranscationTest {

    @Autowired
    private ResponseToComplaintTransactionService responseToComplaintTransactionService;

    @Autowired
    private ResponseToComplaintService responseToComplaintService;

    @Autowired
    private  ServiceService serviceService;

    @Autowired
    public AppUserService appUserService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingForApproveComplaint() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Complaint complaint = createComplaint();
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                responseToComplaintTransactionService.responseToApproveComplaintTransactional(complaint);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Complaint complaint = createComplaint();
                responseToComplaintTransactionService.responseToApproveComplaintTransactional(complaint);
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingForDeclineComplaint() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                ResponseToComplaint response = createResponseToComplaint();
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                responseToComplaintTransactionService.responseToDeclineComplaintTransactional(response, response.getComplaint().getId());
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                ResponseToComplaint response = createResponseToComplaint();
                responseToComplaintTransactionService.responseToDeclineComplaintTransactional(response, response.getComplaint().getId());
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private ResponseToComplaint createResponseToComplaint() {
        Administrator admin = (Administrator) appUserService.findOne(1L);

        ResponseToComplaint responseToComplaint = new ResponseToComplaint();
        responseToComplaint.setComplaint(createComplaint());
        responseToComplaint.setApproved(false);
        responseToComplaint.setDateSubmitted(new Date());
        responseToComplaint.setAdministrator(admin);
        responseToComplaint.setContent("Bas ruzan komentar");

        return  responseToComplaint;
    }

    private Complaint createComplaint() {
        Client client = (Client) appUserService.findOne(1L);
        Service service = serviceService.findById(1L);

        Complaint complaint = new Complaint();
        complaint.setApproved(true);
        complaint.setResponseToComplaint(null);
        complaint.setClient(client);
        complaint.setDateSubmitted(new Date());
        complaint.setContent("Ma nista ne valjda ovo");
        complaint.setService(service);

        return complaint;
    }
}
