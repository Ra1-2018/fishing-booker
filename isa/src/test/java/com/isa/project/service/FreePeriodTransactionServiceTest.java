package com.isa.project.service;

import com.isa.project.model.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreePeriodTransactionServiceTest {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TimeRangeService timeRangeService;

    @Autowired
    private ReservationTransactionService reservationTransactionService;

    @Autowired
    private FreePeriodTransactionService freePeriodTransactionService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingForNewAction() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                TimeRange freePeriod = timeRangeService.findById(1L);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}
                freePeriodTransactionService.removeFreePeriod(freePeriod);
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
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
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
