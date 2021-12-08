package com.isa.project.controller;

import com.isa.project.dto.AppUserDTO;
import com.isa.project.dto.LoginDTO;
import com.isa.project.model.AppUser;
import com.isa.project.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO appUserDTO) {

        if (appUserDTO.getEmail() == null || appUserDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserDTO.getEmail()).size() > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = new AppUser(appUserDTO.getId(), appUserDTO.getEmail(), appUserDTO.getPassword(), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone());
        appUser = appUserService.save(appUser);

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(loginDTO.getEmail()).size() != 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = appUserService.findByEmail(loginDTO.getEmail()).iterator().next();

        if(!appUser.getPassword().equals(loginDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppUserDTO>> findAll() {
        Collection<AppUser> appUsers = appUserService.findAll();
        Collection<AppUserDTO> appUserDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            appUserDTOS.add(new AppUserDTO(appUser));
        }

        return new ResponseEntity<>(appUserDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> findOne(@PathVariable("id") long id) {
        AppUser appUser = appUserService.findOne(id);

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") long id) {
        AppUser appUser = appUserService.findOne(id);
        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appUserService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
