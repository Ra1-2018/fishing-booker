package com.isa.project.service;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.model.Action;
import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TimeRangeService timeRangeService;

    public List<com.isa.project.model.Service> findAll() { return serviceRepository.findAll(); }

    public com.isa.project.model.Service findById(Long id) { return serviceRepository.findById(id).orElse(null); }

    public List<com.isa.project.model.Service> getIfMatchesCriteria(ServiceCriteriaDTO serviceCriteria) {
        List<com.isa.project.model.Service> services = findAll();
        List<com.isa.project.model.Service> matchingServices = new ArrayList<>();
        Date startDate = serviceCriteria.getStartDate();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, serviceCriteria.getDurationInDays());
        Date endDate = c.getTime();
        for (com.isa.project.model.Service service : services) {
            if(!matchesCriteria(serviceCriteria, service)) {
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

    private boolean matchesCriteria(ServiceCriteriaDTO serviceCriteria, com.isa.project.model.Service service) {
        return service.getServiceType() == serviceCriteria.getServiceType() && service.getAddress().toLowerCase().contains(serviceCriteria.getAddress().toLowerCase()) && service.getMaxNumberOfPeople() >= serviceCriteria.getNumberOfPeople() && service.getAverageGrade() >= serviceCriteria.getMinAverageGrade();
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

    public boolean IsActionValid(Action action) {
        com.isa.project.model.Service service = action.getService();
        Date startDate = action.getStartTime();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, action.getDurationInDays());
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
                save(service);
                timeRangeService.deleteById(timeRange.getId());
                return;
            }
        }
        save(service);
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

    public boolean isFreePeriodValid(TimeRange newFreePeriod) {
        Set<TimeRange> freePeriods = newFreePeriod.getService().getFreePeriods();
        Date newEndDate = newFreePeriod.getEndDate();
        Date newStartDate = newFreePeriod.getStartDate();

        if (freePeriods.size() == 0 ) return  true;

        for(TimeRange period : freePeriods) {
            Date oldStartDate = period.getStartDate();
            Date oldEndDate = period.getEndDate();

            if (newStartDate.before(oldStartDate) & newEndDate.after(oldEndDate)) {
                return  false;
            }
            else if (newStartDate.before(oldStartDate) & newEndDate.after(oldStartDate)) {
                return false;
            }
            else if (newEndDate.after(oldEndDate) & newStartDate.before(oldEndDate)) {
                return false;
            }
            else if ( newStartDate.after(oldStartDate) & newEndDate.before(oldEndDate)) {
                return false;
            }
        }
        return true;
    }

    public void addFreePeriod(TimeRange newFreePeriod) {
        com.isa.project.model.Service service = newFreePeriod.getService();
        service.addFreePeriod(newFreePeriod);
        save(service);
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

    public com.isa.project.model.Service save(com.isa.project.model.Service service) {
        service.setLastUpdateDate(new Date());
        return serviceRepository.save(service);
    }
}
