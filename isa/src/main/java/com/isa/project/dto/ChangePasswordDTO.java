package com.isa.project.dto;

import java.util.Date;

public class ChangePasswordDTO {

    private Long userID;
    private String newPassword;

    public ChangePasswordDTO() { }

    public ChangePasswordDTO(Long userID, String newPassword) {
        this.userID = userID;
        this.newPassword = newPassword;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
