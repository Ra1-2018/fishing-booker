package com.isa.project.service;

import com.isa.project.dto.BusinessReportDTO;
import com.isa.project.dto.ServiceDTO;
import com.isa.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class BusinessReportService {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AppUserService appUserService;

    public BusinessReportDTO getBusinessReport(long id) {
        AppUser user = appUserService.findOne(id);
        BusinessReportDTO businessReportDTO = new BusinessReportDTO();

        List<Reservation> reservations = new ArrayList<>();
        List<ServiceDTO> serviceDTOS = new ArrayList<>();

        if(user.getAppUserType() == AppUserType.BOAT_OWNER) {
            List<Boat> boats = boatService.findBoatsByOwner((BoatOwner) user);
            for(Boat boat : boats) {
                reservations.addAll(reservationService.findByService(boat));
                serviceDTOS.add(new ServiceDTO(boat));
            }
        }
        else if ( user.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            List<Cottage> cottages = cottageService.findCottagesByOwner((CottageOwner) user);
            for(Cottage cottage : cottages) {
                reservations.addAll(reservationService.findByService(cottage));
                serviceDTOS.add(new ServiceDTO(cottage));
            }
        }
        else if ( user.getAppUserType() == AppUserType.INSTRUCTOR) {
            List<Adventure> adventures = adventureService.findAdventureByInstructor((Instructor) user);

            for(Adventure adventure : adventures) {
                reservations.addAll(reservationService.findByService(adventure));
                serviceDTOS.add(new ServiceDTO(adventure));
            }
        } else if ( user.getAppUserType() == AppUserType.ADMIN) {
            reservations.addAll(reservationService.findAll());
            for (com.isa.project.model.Service service: serviceService.findAll()) {
                serviceDTOS.add(new ServiceDTO(service));
            }
        }

        businessReportDTO.setWeeklyEarnings(getWeeklyEarnings(reservations));
        businessReportDTO.setMonthlyEarnings(getMonthlyEarnings(reservations));
        businessReportDTO.setYearlyEarnings(getYearlyEarnings(reservations));

        businessReportDTO.setWeeklyReservations(getWeeklyReservations(reservations));
        businessReportDTO.setMonthlyReservations(getMonthlyReservations(reservations));
        businessReportDTO.setYearlyReservations(getYearlyReservations(reservations));

        businessReportDTO.setServices(serviceDTOS);

        return businessReportDTO;
    }

    public double getWeeklyEarnings(List<Reservation> reservations) {
        double weeklyEarnings = 0;

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -7);
        Date startDate = c.getTime();

        for(Reservation reservation : reservations) {
            if(reservation.getReservationStartDateAndTime().after(startDate) && reservation.getReservationStartDateAndTime().before(endDate)) {
                weeklyEarnings += reservation.getPrice();
            }
        }
        return weeklyEarnings;
    }

    public double getMonthlyEarnings(List<Reservation> reservations) {
        double monthlyEarnings = 0;

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -30);
        Date startDate = c.getTime();

        for(Reservation reservation : reservations) {
            if(reservation.getReservationStartDateAndTime().after(startDate) && reservation.getReservationStartDateAndTime().before(endDate)) {
                monthlyEarnings += reservation.getPrice();
            }
        }
        return monthlyEarnings;
    }

    public double getYearlyEarnings(List<Reservation> reservations) {
        double yearlyEarnings = 0;

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -365);
        Date startDate = c.getTime();

        for(Reservation reservation : reservations) {
            if(reservation.getReservationStartDateAndTime().after(startDate) && reservation.getReservationStartDateAndTime().before(endDate)) {
                yearlyEarnings += reservation.getPrice();
            }
        }
        return yearlyEarnings;
    }


    public int[] getWeeklyReservations(List<Reservation> reservations) {

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -7);
        Date startDate = c.getTime();
        int[] weeklyReservations = {0, 0, 0, 0, 0, 0, 0};
        for(Reservation reservation : reservations) {
            Date reservationStart = reservation.getReservationStartDateAndTime();
            c.setTime(reservationStart);
            c.add(Calendar.DATE, reservation.getDurationInDays());
            Date reservationEnd = c.getTime();
            if(reservationStart.after(startDate) && reservationStart.before(endDate)) {
                long startDiff = reservationStart.getTime() - startDate.getTime();
                int graphStart = (int) TimeUnit.DAYS.convert(startDiff, TimeUnit.MILLISECONDS);
                long endDiff = reservationEnd.getTime() - startDate.getTime();
                int graphEnd = (int) TimeUnit.DAYS.convert(endDiff, TimeUnit.MILLISECONDS);

                for(int i=graphStart; i<graphEnd; i++) {

                    if(i== weeklyReservations.length) {
                        break;
                    }
                    weeklyReservations[i] += 1;
                }
            }
        }
        return weeklyReservations;
    }

    public int[] getMonthlyReservations(List<Reservation> reservations) {

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -30);
        Date startDate = c.getTime();
        int[] monthlyReservations = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(Reservation reservation : reservations) {
            Date reservationStart = reservation.getReservationStartDateAndTime();
            c.setTime(reservationStart);
            c.add(Calendar.DATE, reservation.getDurationInDays());
            Date reservationEnd = c.getTime();
            if(reservationStart.after(startDate) && reservationStart.before(endDate)) {
                long startDiff = reservationStart.getTime() - startDate.getTime();
                int graphStart = (int) TimeUnit.DAYS.convert(startDiff, TimeUnit.MILLISECONDS);
                long endDiff = reservationEnd.getTime() - startDate.getTime();
                int graphEnd = (int) TimeUnit.DAYS.convert(endDiff, TimeUnit.MILLISECONDS);

                for(int i=graphStart; i<graphEnd; i++) {

                    if(i== monthlyReservations.length) {
                        break;
                    }
                    monthlyReservations[i] += 1;
                }
            }
        }
        return monthlyReservations;
    }

    public int[] getYearlyReservations(List<Reservation> reservations) {

        Date endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -365);
        Date startDate = c.getTime();
        int[] yearlyReservations = new int[365];
        for(int i=0; i<365; i++) {
            yearlyReservations[i] = 0;
        }
        for(Reservation reservation : reservations) {
            Date reservationStart = reservation.getReservationStartDateAndTime();
            c.setTime(reservationStart);
            c.add(Calendar.DATE, reservation.getDurationInDays());
            Date reservationEnd = c.getTime();
            if(reservationStart.after(startDate) && reservationStart.before(endDate)) {
                long startDiff = reservationStart.getTime() - startDate.getTime();
                int graphStart = (int) TimeUnit.DAYS.convert(startDiff, TimeUnit.MILLISECONDS);
                long endDiff = reservationEnd.getTime() - startDate.getTime();
                int graphEnd = (int) TimeUnit.DAYS.convert(endDiff, TimeUnit.MILLISECONDS);

                for(int i=graphStart; i<graphEnd; i++) {

                    if(i== yearlyReservations.length) {
                        break;
                    }
                    yearlyReservations[i] += 1;
                }
            }
        }
        return yearlyReservations;
    }
}
