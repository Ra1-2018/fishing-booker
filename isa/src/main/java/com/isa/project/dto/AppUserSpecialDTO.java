package com.isa.project.dto;

public class AppUserSpecialDTO {
    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String telephone;
    private String userType;
    private String explanation;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUserType() {
        return userType;
    }

    public String getExplanation() {
        return explanation;
    }

    public AppUserSpecialDTO() { }

    public AppUserSpecialDTO(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, String userType, String explanation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.userType = userType;
        this.explanation = explanation;
    }
}
