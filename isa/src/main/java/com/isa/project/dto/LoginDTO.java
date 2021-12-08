package com.isa.project.dto;

public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
