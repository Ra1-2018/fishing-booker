package com.isa.project.dto;

import com.isa.project.model.ServiceType;

import java.util.Date;

public class ServiceCriteriaDTO {
    private ServiceType serviceType;
    private Date startDate;
    private int durationInDays;
    private String address;
    private int numberOfPeople;
    private double minAverageGrade;

    public ServiceCriteriaDTO() {}

    public ServiceCriteriaDTO(ServiceType serviceType, Date startDate, int durationInDays, String address, int numberOfPeople, double minAverageGrade) {
        this.serviceType = serviceType;
        this.startDate = startDate;
        this.durationInDays = durationInDays;
        this.address = address;
        this.numberOfPeople = numberOfPeople;
        this.minAverageGrade = minAverageGrade;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public double getMinAverageGrade() {
        return minAverageGrade;
    }
}
