package com.isa.project.dto;

import com.isa.project.model.AppUser;
import com.isa.project.model.BoatOwner;
import org.apache.juli.logging.Log;

public class LoginResponseDTO {
    private long id;
    private String userType;

    public long getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public LoginResponseDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.userType = appUser.getClass().getSimpleName();
    }

    public LoginResponseDTO() { }

    public LoginResponseDTO(long id, String userType) {
        this.id = id;
        this.userType = userType;
    }
}
