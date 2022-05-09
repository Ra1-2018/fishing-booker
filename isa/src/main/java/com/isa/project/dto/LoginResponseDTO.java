package com.isa.project.dto;

import com.isa.project.model.AppUser;
import com.isa.project.model.AppUserType;
import com.isa.project.model.Client;

public class LoginResponseDTO {
    private long id;
    private AppUserType appUserType;
    private UserTokenState userTokenState;
    private boolean firstReg;
    private boolean sanctioned;

    public long getId() {
        return id;
    }

    public AppUserType getAppUserType() {
        return appUserType;
    }

    public UserTokenState getUserTokenState() {
        return userTokenState;
    }

    public boolean isFirstReg() { return firstReg; }

    public boolean isSanctioned() {
        return sanctioned;
    }

    public LoginResponseDTO() { }

    public LoginResponseDTO(AppUser appUser, UserTokenState userTokenState, boolean firstReg) {
        this.id = appUser.getId();
        this.appUserType = appUser.getAppUserType();
        this.userTokenState = userTokenState;
        this.firstReg = firstReg;
        if(appUser.getAppUserType() == AppUserType.CLIENT) {
            Client client = (Client) appUser;
            this.sanctioned = client.isSanctioned();
        } else {
            this.sanctioned = false;
        }
    }
}
