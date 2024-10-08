package com.isa.project.service;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.model.Action;
import com.isa.project.model.Client;
import com.isa.project.model.Reservation;
import com.isa.project.model.TimeRange;
import com.isa.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.*;

@Service
//@Transactional(readOnly = true)
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TimeRangeService timeRangeService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ActionService actionService;

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

    public List<com.isa.project.model.Service> searchServices(ServiceCriteriaDTO serviceCriteria) {
        List<com.isa.project.model.Service> services = findAll();
        List<com.isa.project.model.Service> matchingServices = new ArrayList<>();
        for (com.isa.project.model.Service service : services) {
            if(matchesCriteria(serviceCriteria, service)) {
                matchingServices.add(service);
            }
        }
        return matchingServices;
    }

    private boolean matchesCriteria(ServiceCriteriaDTO serviceCriteria, com.isa.project.model.Service service) {
        return service.getServiceType() == serviceCriteria.getServiceType() && service.getMaxNumberOfPeople() >= serviceCriteria.getNumberOfPeople() && service.getAverageGrade() >= serviceCriteria.getMinAverageGrade() && (service.getLocation().getCity() == null || service.getLocation() == null || service.getLocation().getCity().toLowerCase().contains(serviceCriteria.getAddress().toLowerCase()) || service.getLocation().getStreet().toLowerCase().contains(serviceCriteria.getAddress().toLowerCase()));
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

        for (TimeRange timeRange : service.getFreePeriods()) {
            if (startDate.after(timeRange.getStartDate()) && endDate.before(timeRange.getEndDate())) {
                return true;
            }
        }
        return false;
    }

    public void RemoveFreePeriodTimeRange(TimeRange timeRange) {
        com.isa.project.model.Service service = timeRange.getService();
        service.removeFreePeriod(timeRange);
        save(service);
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

    public void RemoveFreePeriodAction(Action action) {
        com.isa.project.model.Service service = action.getService();
        Date actionStartTime = action.getStartTime();
        Calendar c = Calendar.getInstance();
        c.setTime(actionStartTime);
        c.add(Calendar.DATE, action.getDurationInDays());
        Date actionEndTime = c.getTime();
        for(TimeRange timeRange : service.getFreePeriods()) {
            if(actionStartTime.after(timeRange.getStartDate()) && actionEndTime.before(timeRange.getEndDate())) {
                TimeRange timeRange1 = new TimeRange(null, timeRange.getStartDate(), actionStartTime, service);
                TimeRange timeRange2 = new TimeRange(null, actionEndTime, timeRange.getEndDate(), service);
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

    public boolean isPeriodValid(TimeRange newPeriod) {
        Set<TimeRange> freePeriods = newPeriod.getService().getFreePeriods();
        Set<TimeRange> unavailablePeriods = newPeriod.getService().getUnavailablePeriods();
        Set<TimeRange> allPeriods = newPeriod.getService().getAllPeriods();
        Date newEndDate = newPeriod.getEndDate();
        Date newStartDate = newPeriod.getStartDate();

        if (freePeriods.size() == 0 && unavailablePeriods.size() == 0) return  true;

        for(Reservation reservation : reservationService.findByService(newPeriod.getService())) {
            Calendar calendar = Calendar.getInstance();
            Date reservationStartDate = reservation.getReservationStartDateAndTime();
            calendar.setTime(reservationStartDate);
            calendar.add(Calendar.DATE, reservation.getDurationInDays());
            Date reservationEndDate = calendar.getTime();
            if (newStartDate.after(reservationStartDate) && newStartDate.before(reservationEndDate)) {
                return false;
            }
            if (newEndDate.after(reservationStartDate) && newEndDate.before(reservationEndDate)) {
                return false;
            }
        }

        for(Action action : actionService.findByService(newPeriod.getService())) {
            Calendar calendar = Calendar.getInstance();
            Date actionStartDate = action.getStartTime();
            calendar.setTime(actionStartDate);
            calendar.add(Calendar.DATE, action.getDurationInDays());
            Date actionEdnDate = calendar.getTime();
            if (newStartDate.after(actionStartDate) && newStartDate.before(actionEdnDate)) {
                return false;
            }
            if (newEndDate.after(actionStartDate) && newEndDate.before(actionEdnDate)) {
                return false;
            }
        }

        for(TimeRange period : allPeriods) {
            Date oldStartDate = period.getStartDate();
            Date oldEndDate = period.getEndDate();

            if (newStartDate.before(oldStartDate) && newEndDate.after(oldEndDate)) {
                return  false;
            }
            else if (newStartDate.before(oldStartDate) && newEndDate.after(oldStartDate)) {
                return false;
            }
            else if (newEndDate.after(oldEndDate) && newStartDate.before(oldEndDate)) {
                return false;
            }
            else if ( newStartDate.after(oldStartDate) && newEndDate.before(oldEndDate)) {
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

    public void addUnavailablePeriod(TimeRange newUnavailablePeriod) {
        com.isa.project.model.Service service = newUnavailablePeriod.getService();
        service.addUnavailablePeriod(newUnavailablePeriod);
        save(service);
    }

    public Collection<com.isa.project.model.Service> getServicesFromReservations(Client client) {
        Collection<Reservation> reservations = reservationService.findByClient(client);
        Collection<com.isa.project.model.Service> services = new ArrayList<>();
        for(Reservation reservation : reservations) {
            com.isa.project.model.Service service = reservation.getService();
            if(!services.contains(service)) {
                services.add(service);
            }
        }
        return services;
    }

    //@Transactional(readOnly = false)
    @CachePut(value = "service", key = "#service.id")
    public com.isa.project.model.Service save(com.isa.project.model.Service service) {
        service.setLastUpdateDate(new Date());
        return serviceRepository.save(service);
    }
}
