package com.isa.project.service;

import com.isa.project.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTransactionServiceTest {

    @Autowired
    private ReservationTransactionService reservationTransactionService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private ReservationService reservationService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    @Transactional
    @Rollback(true)
    public void testOptimisticLockingForOrdinaryReservation() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Reservation reservation = createReservation();
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                reservationTransactionService.makeRegularReservation(reservation);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Reservation reservation = createReservation();
                reservationTransactionService.makeRegularReservation(reservation);
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
    @Transactional
    @Rollback(true)
    public void testOptimisticLockingForSpecialReservation() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Action action = actionService.findById(1L);
                Client client = (Client) appUserService.findOne(1L);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                reservationTransactionService.makeReservationFromAction(action, client);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Action action = actionService.findById(1L);
                Client client = (Client) appUserService.findOne(1L);
                reservationTransactionService.makeReservationFromAction(action, client);
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
    @Transactional
    @Rollback(true)
    public void testOptimisticLockingForCancellingReservation() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Reservation reservation = reservationService.findById(1L);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                reservationTransactionService.cancelReservation(reservation);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Service service = serviceService.findById(1L);
                serviceService.save(service);
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

    private Reservation createReservation() {
        Service service = serviceService.findById(1L);

        Client client = (Client) appUserService.findOne(1L);

        Reservation reservation = new Reservation();
        reservation.setReservationStartDateAndTime(new Date());
        reservation.setDurationInDays(3);
        reservation.setNumberOfPeople(3);
        reservation.setPrice(100);
        reservation.setClient(client);
        reservation.setService(service);
        return reservation;
    }
}
