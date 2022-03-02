package com.isa.project.service;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TimeRangeService timeRangeService;

    public List<com.isa.project.model.Service> findAll() { return serviceRepository.findAll(); }

    public com.isa.project.model.Service findById(Long id) { return serviceRepository.findById(id).orElse(null); }

    public Collection<com.isa.project.model.Service> getIfMatchesCriteria(ServiceCriteriaDTO serviceCriteria) {
        Collection<com.isa.project.model.Service> services = findAll();
        Collection<com.isa.project.model.Service> matchingServices = new ArrayList<>();
        Date startDate = serviceCriteria.getStartDate();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, serviceCriteria.getDurationInDays());
        Date endDate = c.getTime();
        for (com.isa.project.model.Service service : services) {
            if(!(service.getServiceType() == serviceCriteria.getServiceType() && service.getAddress().toLowerCase().contains(serviceCriteria.getAddress().toLowerCase()) && service.getMaxNumberOfPeople() >= serviceCriteria.getNumberOfPeople())) {
                continue;
            }
            for(TimeRange timeRange : service.getFreePeriods()) {
                if(startDate.after(timeRange.getStartDate()) && endDate.before(timeRange.getEndDate())) {
                    matchingServices.add(service);
                    break;
                }
            }
        }
        return matchingServices;
    }

    public boolean IsReservationValid(Reservation reservation) {
        com.isa.project.model.Service service = reservation.getService();
        Date startDate = reservation.getReservationStartDateAndTime();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, reservation.getDurationInDays());
        Date endDate = c.getTime();
        for(TimeRange timeRange : service.getFreePeriods()) {
            if(startDate.after(timeRange.getStartDate()) && endDate.before(timeRange.getEndDate())) {
                return true;
            }
        }
        return false;
    }

    public void RemoveFreePeriod(Reservation reservation) {
        com.isa.project.model.Service service = reservation.getService();
        Date reservationStartTime = reservation.getReservationStartDateAndTime();
        Calendar c = Calendar.getInstance();
        c.setTime(reservationStartTime);
        c.add(Calendar.DATE, reservation.getDurationInDays());
        Date reservationEndTime = c.getTime();
        for(TimeRange timeRange : service.getFreePeriods()) {
            if(reservationStartTime.after(timeRange.getStartDate()) && reservationEndTime.before(timeRange.getEndDate())) {
                TimeRange timeRange1 = new TimeRange(null, timeRange.getStartDate(), reservationStartTime, service);
                TimeRange timeRange2 = new TimeRange(null, reservationEndTime, timeRange.getEndDate(), service);
                service.removeFreePeriod(timeRange);
                timeRangeService.save(timeRange);
                service.addFreePeriod(timeRange1);
                service.addFreePeriod(timeRange2);
                serviceRepository.save(service);
                timeRangeService.deleteById(timeRange.getId());
            }
        }
    }

    public void RestoreFreePeriod(Reservation reservation) {
        com.isa.project.model.Service service = reservation.getService();
        Date reservationStartTime = reservation.getReservationStartDateAndTime();
        Calendar c = Calendar.getInstance();
        c.setTime(reservationStartTime);
        c.add(Calendar.DATE, reservation.getDurationInDays());
        Date reservationEndTime = c.getTime();
        TimeRange freePeriodBefore = null;
        TimeRange freePeriodAfter = null;
        for(TimeRange timeRange: service.getFreePeriods()) {
            if(reservationStartTime.compareTo(timeRange.getEndDate()) == 0) {
                freePeriodBefore = timeRange;
            }
            else if(reservationEndTime.compareTo(timeRange.getStartDate()) == 0) {
                freePeriodAfter = timeRange;
            }
        }
        if(freePeriodBefore != null && freePeriodAfter != null) {
            TimeRange restoredFreePeriod = new TimeRange();
            restoredFreePeriod.setService(service);
            restoredFreePeriod.setStartDate(freePeriodBefore.getStartDate());
            restoredFreePeriod.setEndDate(freePeriodAfter.getEndDate());
            service.removeFreePeriod(freePeriodBefore);
            service.removeFreePeriod(freePeriodAfter);
            timeRangeService.save(freePeriodBefore);
            timeRangeService.save(freePeriodAfter);
            timeRangeService.deleteById(freePeriodBefore.getId());
            timeRangeService.deleteById(freePeriodAfter.getId());
            timeRangeService.save(restoredFreePeriod);
        }
        else if(freePeriodBefore != null) {
            TimeRange restoredFreePeriod = new TimeRange();
            restoredFreePeriod.setService(service);
            restoredFreePeriod.setStartDate(freePeriodBefore.getStartDate());
            restoredFreePeriod.setEndDate(reservationEndTime);
            service.removeFreePeriod(freePeriodBefore);
            timeRangeService.save(freePeriodBefore);
            timeRangeService.deleteById(freePeriodBefore.getId());
            timeRangeService.save(restoredFreePeriod);
        }
        else if(freePeriodAfter != null) {
            TimeRange restoredFreePeriod = new TimeRange();
            restoredFreePeriod.setService(service);
            restoredFreePeriod.setStartDate(reservationStartTime);
            restoredFreePeriod.setEndDate(freePeriodAfter.getEndDate());
            service.removeFreePeriod(freePeriodAfter);
            timeRangeService.save(freePeriodAfter);
            timeRangeService.deleteById(freePeriodAfter.getId());
            timeRangeService.save(restoredFreePeriod);
        }
        else {
            TimeRange restoredFreePeriod = new TimeRange();
            restoredFreePeriod.setService(service);
            restoredFreePeriod.setStartDate(reservationStartTime);
            restoredFreePeriod.setEndDate(reservationEndTime);
            timeRangeService.save(restoredFreePeriod);
        }
    }

    public Collection<com.isa.project.model.Service> getServicesFromReservations(Client client) {
        Collection<Reservation> reservations = client.getReservations();
        Collection<com.isa.project.model.Service> services = new ArrayList<>();
        for(Reservation reservation : reservations) {
            com.isa.project.model.Service service = reservation.getService();
            if(!services.contains(service)) {
                services.add(service);
            }
        }
        return services;
    }
}
