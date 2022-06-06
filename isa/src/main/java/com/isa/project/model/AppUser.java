package com.isa.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "userSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
    private long id;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String telephone;
    @Column
    private boolean enabled;
    @Column
    private AppUserType appUserType;
    @Column(nullable = true)
    private int points;
    @Column AppUserLoyaltyType loyaltyType;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_user_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<UserRole> userRoles;

    public AppUser() {
    }

    public AppUser(long id, String email, String password, String name, String surname, String address, String city, String country, String telephone, AppUserType appUserType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.enabled = false;
        this.appUserType = appUserType;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AppUserType getAppUserType() {
        return appUserType;
    }

    public void setAppUserType(AppUserType appUserType) {
        this.appUserType = appUserType;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public AppUserLoyaltyType getLoyaltyType() {
        return loyaltyType;
    }

    public void setLoyaltyType(AppUserLoyaltyType loyaltyType) {
        this.loyaltyType = loyaltyType;
    }
}
