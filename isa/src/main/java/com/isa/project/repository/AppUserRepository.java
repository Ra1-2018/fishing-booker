package com.isa.project.repository;

import com.isa.project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("select u from AppUser u where u.email = ?1")
    public AppUser findByEmail(String email);
}
