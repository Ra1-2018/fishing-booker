package com.isa.project.service;

import com.isa.project.model.*;
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
public class ActionTransactionServiceTest {

    @Autowired
    private ReservationTransactionService reservationTransactionService;

    @Autowired
    private ActionTransactionService actionTransactionService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private ReservationService reservationService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingForNewAction() throws Throwable {

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
                Action action = createAction();
                actionTransactionService.createAction(action);
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
        Service service = serviceService.findById(79L);

        Client client = (Client) appUserService.findOne(81L);

        Reservation reservation = new Reservation();
        reservation.setReservationStartDateAndTime(new Date());
        reservation.setDurationInDays(3);
        reservation.setNumberOfPeople(3);
        reservation.setPrice(100);
        reservation.setClient(client);
        reservation.setService(service);
        return reservation;
    }

    private Action createAction() {
        Service service = serviceService.findById(79L);

        Action action = new Action();
        action.setMaxNumberOfPeople(3);
        action.setDurationInDays(3);
        action.setPrice(50);
        action.setStartTime(new Date());
        action.setService(service);
        return action;
    }
}
