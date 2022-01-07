package com.isa.project.service;

import com.isa.project.model.UserRole;
import com.isa.project.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository roleRepository;

    public UserRole findById(Long id) {
        UserRole auth = this.roleRepository.getOne(id);
        return auth;
    }

    public List<UserRole> findByName(String name) {
        List<UserRole> roles = this.roleRepository.findByName(name);
        return roles;
    }
}
