package com.isa.project.dto;

import com.isa.project.model.AppUser;
import com.isa.project.model.AppUserType;

public class LoginResponseDTO {
    private long id;
    private AppUserType appUserType;
    private UserTokenState userTokenState;
    private boolean firstReg;

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

    public LoginResponseDTO(AppUser appUser, UserTokenState userTokenState) {
        this.id = appUser.getId();
        this.appUserType = appUser.getAppUserType();
        this.userTokenState = userTokenState;
    }

    public LoginResponseDTO() { }

    public LoginResponseDTO(long id, AppUserType appUserType, UserTokenState userTokenState) {
        this.id = id;
        this.appUserType = appUserType;
        this.userTokenState = userTokenState;
    }

    public LoginResponseDTO(AppUser appUser, UserTokenState userTokenState, boolean firstReg) {
        this.id = appUser.getId();
        this.appUserType = appUser.getAppUserType();
        this.userTokenState = userTokenState;
        this.firstReg = firstReg;
    }
}
