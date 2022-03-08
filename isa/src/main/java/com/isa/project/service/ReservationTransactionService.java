package com.isa.project.service;

import com.isa.project.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationTransactionService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceService serviceService;

    @Transactional
    public void makeRegularReservation(Reservation reservation) {
        try {
            reservationService.save(reservation);
            serviceService.RemoveFreePeriod(reservation);
        } catch (ObjectOptimisticLockingFailureException e) {}
    }
}
