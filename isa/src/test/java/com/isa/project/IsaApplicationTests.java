package com.isa.project;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.model.*;
import com.isa.project.service.ActionService;
import com.isa.project.service.AppUserService;
import com.isa.project.service.ReservationTransactionService;
import com.isa.project.service.ServiceService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsaApplicationTests {

	@Autowired
	private ReservationTransactionService reservationTransactionService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private ActionService actionService;

	@Test(expected = ObjectOptimisticLockingFailureException.class)
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
	public void testOptimisticLockingForSpecialReservation() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");
				Action action = actionService.findById(1L);
				Client client = (Client) appUserService.findOne(49L);
				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
				reservationTransactionService.makeReservationFromAction(action, client);
			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				Action action = actionService.findById(1L);
				Client client = (Client) appUserService.findOne(49L);
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

	private Reservation createReservation() {
		Service service = serviceService.findById(7L);

		Client client = (Client) appUserService.findOne(49L);

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
