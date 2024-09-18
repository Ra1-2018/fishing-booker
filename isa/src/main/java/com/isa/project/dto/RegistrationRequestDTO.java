package com.isa.project.dto;

import com.isa.project.model.AppUser;
import com.isa.project.model.RegistrationRequest;

public class RegistrationRequestDTO {

    private Long id;
    private String explanation;
    private AppUserDTO user;
    private boolean approved;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(RegistrationRequest request) {

        this(request.getId(), request.getExplanation(), new AppUserDTO(request.getUser()), request.isApproved());
    }

    public RegistrationRequestDTO(Long id, String explanation, AppUserDTO user, boolean approved) {
        this.id = id;
        this.explanation = explanation;
        this.user = user;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public String getExplanation() {
        return explanation;
    }

    public AppUserDTO getUser() {
        return user;
    }

    public boolean isApproved() {
        return approved;
    }

}
