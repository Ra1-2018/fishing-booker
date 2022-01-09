package com.isa.project.dto;

import com.isa.project.model.AppUser;
import com.isa.project.model.BoatOwner;
import org.apache.juli.logging.Log;

public class LoginResponseDTO {
    private long id;
    private String userType;
    private UserTokenState userTokenState;

    public long getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public UserTokenState getUserTokenState() {
        return userTokenState;
    }

    public LoginResponseDTO(AppUser appUser, UserTokenState userTokenState) {
        this.id = appUser.getId();
        this.userType = appUser.getClass().getSimpleName();
        this.userTokenState = userTokenState;
    }

    public LoginResponseDTO() { }

    public LoginResponseDTO(long id, String userType, UserTokenState userTokenState) {
        this.id = id;
        this.userType = userType;
        this.userTokenState = userTokenState;
    }
}
