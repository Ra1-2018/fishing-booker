package com.isa.project.service;

import com.isa.project.model.Action;
import com.isa.project.model.AdditionalService;
import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class ReservationTransactionService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ActionService actionService;

    @Transactional(readOnly = false)
    public void makeRegularReservation(Reservation reservation) {
            reservationService.save(reservation);
            serviceService.RemoveFreePeriod(reservation);
    }

    @Transactional(readOnly = false)
    public Reservation makeReservationFromAction(Action action, Client client) {
        Reservation reservation = new Reservation();
        reservation.setReservationStartDateAndTime(action.getStartTime());
        reservation.setDurationInDays(action.getDurationInDays());
        reservation.setNumberOfPeople(action.getMaxNumberOfPeople());
        for(AdditionalService additionalService : action.getAdditionalServices()) {
            reservation.addAdditionalService(additionalService);
        }
        reservation.setPrice(action.getPrice());
        reservation.setClient(client);
        reservation.setService(action.getService());
        com.isa.project.model.Service service = reservation.getService();
        service.removeAction(action);
        serviceService.save(service);
        actionService.deleteById(action.getId());
        return reservationService.save(reservation);
    }
}
