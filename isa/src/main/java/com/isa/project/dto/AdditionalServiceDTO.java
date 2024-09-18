package com.isa.project.dto;

import com.isa.project.model.AdditionalService;

public class AdditionalServiceDTO {
    private long id;
    private String name;
    private long price;
    private long serviceId;

    public AdditionalServiceDTO() {}

    public AdditionalServiceDTO(long id, String name, long price, long serviceId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceId = serviceId;
    }

    public AdditionalServiceDTO(AdditionalService service) { this(service.getId(), service.getName(), service.getPrice(), service.getService().getId());}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public long getServiceId() { return serviceId; }
}
