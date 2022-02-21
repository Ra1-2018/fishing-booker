package com.isa.project.dto;

import com.isa.project.model.AdditionalService;

public class AdditionalServiceDTO {
    private long id;
    private String name;
    private long price;

    public AdditionalServiceDTO() {}

    public AdditionalServiceDTO(long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public AdditionalServiceDTO(AdditionalService service) { this(service.getId(), service.getName(), service.getPrice());}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
