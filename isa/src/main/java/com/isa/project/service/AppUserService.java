package com.isa.project.service;

import com.isa.project.model.AppUser;
import com.isa.project.model.UserRole;
import com.isa.project.repository.AppUserRepository;
import com.isa.project.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleService roleService;

    public AppUser findOne(long id) { return appUserRepository.findById(id).orElse(null); }
    public AppUser findOneByEmail(String email) { return appUserRepository.findByEmail(email); }

    public List<AppUser> findAll() { return appUserRepository.findAll(); }

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser saveClient(AppUser appUser) {
        List<UserRole> roles = roleService.findByName("ROLE_USER");
        roles.addAll(roleService.findByName("ROLE_CLIENT"));
        appUser.setUserRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser saveBoatOwner(AppUser appUser) {
        List<UserRole> roles = roleService.findByName("ROLE_USER");
        roles.addAll(roleService.findByName("ROLE_BOAT_OWNER"));
        appUser.setUserRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser saveCottageOwner(AppUser appUser) {
        List<UserRole> roles = roleService.findByName("ROLE_USER");
        roles.addAll(roleService.findByName("ROLE_COTTAGE_OWNER"));
        appUser.setUserRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser saveInstructor(AppUser appUser) {
        List<UserRole> roles = roleService.findByName("ROLE_USER");
        roles.addAll(roleService.findByName("ROLE_INSTRUCTOR"));
        appUser.setUserRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser saveAdministrator(AppUser appUser) {
        List<UserRole> roles = roleService.findByName("ROLE_USER");
        roles.addAll(roleService.findByName("ROLE_ADMIN"));
        appUser.setUserRoles(roles);
        return appUserRepository.save(appUser);
    }

    public void remove(long id) { appUserRepository.deleteById(id); }

    public AppUser findByEmail(String email) { return appUserRepository.findByEmail(email); }
}
