package com.isa.project.dto;

import com.isa.project.model.TimeRange;

import java.util.Date;

public class TimeRangeDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private long serviceId;

    private boolean available;

    public TimeRangeDTO() {}

    public TimeRangeDTO(long id, Date startDate, Date endDate, long serviceId, boolean available) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceId = serviceId;
        this.available = available;
    }

    public TimeRangeDTO(TimeRange timeRange) {
        this.id = timeRange.getId();
        this.startDate = timeRange.getStartDate();
        this.endDate = timeRange.getEndDate();
        this.serviceId = timeRange.getService().getId();
        this.available = timeRange.isAvailable();
    }

    public long getId() { return id; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

    public long getServiceId() { return serviceId; }

    public boolean isAvailable() { return available;}
    
    public void setId(long id) { this.id = id; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public void setServiceId(long serviceId) { this.serviceId = serviceId; }
}
