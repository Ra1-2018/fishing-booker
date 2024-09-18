package com.isa.project.dto;

import java.util.List;

public class BusinessReportDTO {
    private int[] weeklyReservations;
    private int[] monthlyReservations;
    private int[] yearlyReservations;
    private double weeklyEarnings;
    private double monthlyEarnings;
    private double yearlyEarnings;
    private List<ServiceDTO> services;

    public BusinessReportDTO() {}

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    public double getWeeklyEarnings() {
        return weeklyEarnings;
    }

    public void setWeeklyEarnings(double weeklyEarnings) {
        this.weeklyEarnings = weeklyEarnings;
    }

    public double getMonthlyEarnings() {
        return monthlyEarnings;
    }

    public void setMonthlyEarnings(double monthlyEarnings) {
        this.monthlyEarnings = monthlyEarnings;
    }

    public double getYearlyEarnings() {
        return yearlyEarnings;
    }

    public void setYearlyEarnings(double yearlyEarnings) {
        this.yearlyEarnings = yearlyEarnings;
    }

    public int[] getWeeklyReservations() {
        return weeklyReservations;
    }

    public void setWeeklyReservations(int[] weeklyReservations) {
        this.weeklyReservations = weeklyReservations;
    }

    public int[] getMonthlyReservations() {
        return monthlyReservations;
    }

    public void setMonthlyReservations(int[] monthlyReservations) {
        this.monthlyReservations = monthlyReservations;
    }

    public int[] getYearlyReservations() {
        return yearlyReservations;
    }

    public void setYearlyReservations(int[] yearlyReservations) {
        this.yearlyReservations = yearlyReservations;
    }
}
