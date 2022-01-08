package com.isa.project.controller;

import com.isa.project.dto.AppUserDTO;
import com.isa.project.dto.AppUserSpecialDTO;
import com.isa.project.dto.LoginDTO;
import com.isa.project.dto.LoginResponseDTO;
import com.isa.project.model.*;
import com.isa.project.service.AppUserService;
import com.isa.project.service.RegistrationRequestService;
import com.isa.project.verification.EmailService;
import com.isa.project.verification.VerificationToken;
import com.isa.project.verification.VerificationTokenService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RegistrationRequestService requestService;

    @Autowired
    private EmailService emailService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO appUserDTO) {

        if (appUserDTO.getEmail() == null || appUserDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = new Client(appUserDTO.getId(), appUserDTO.getEmail(), appUserDTO.getPassword(), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone());
        appUser = appUserService.save(appUser);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(null, token, appUser, new Date());
        verificationTokenService.save(verificationToken);

        try {
            emailService.sendNotificaitionAsync(appUser, token);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/special")
    public ResponseEntity<AppUserDTO> saveSpecialUser(@RequestBody AppUserSpecialDTO appUserSpecialDTO) {

        if (appUserSpecialDTO.getEmail() == null || appUserSpecialDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserSpecialDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (appUserSpecialDTO.getUserType().equals("Boat owner")) {
            AppUser boatOwner = new BoatOwner(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), appUserSpecialDTO.getPassword(), appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            boatOwner.setEnabled(true);
            boatOwner = appUserService.save(boatOwner);


            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), boatOwner);
            requestService.save(request);
        }
        else if (appUserSpecialDTO.getUserType().equals("Cottage owner")) {
            AppUser cottageOwner = new CottageOwner(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), appUserSpecialDTO.getPassword(), appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            cottageOwner.setEnabled(true);
            cottageOwner = appUserService.save(cottageOwner);

            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), cottageOwner);
            requestService.save(request);
        }
        else if(appUserSpecialDTO.getUserType().equals("Instructor")) {
            AppUser instructor = new Instructor(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), appUserSpecialDTO.getPassword(), appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            instructor.setEnabled(true);
            instructor = appUserService.save(instructor);

            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), instructor);
            requestService.save(request);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/activate/{token}")
    public RedirectView activate(@PathVariable("token") String token) {

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        //if (verificationToken == null) {
        //String message = messages.getMessage("auth.message.invalidToken", null, locale);
        //model.addAttribute("message", message);
        //return "redirect:/badUser.html?lang=" + locale.getLanguage();
        //}

        AppUser appUser = verificationToken.getAppUser();
        //Calendar cal = Calendar.getInstance();
        //if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        //String messageValue = messages.getMessage("auth.message.expired", null, locale)
        //model.addAttribute("message", messageValue);
        //return "redirect:/badUser.html?lang=" + locale.getLanguage();
        //}

        appUser.setEnabled(true);
        appUserService.save(appUser);

        return new RedirectView("http://localhost:4200/login");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/approve/{id}")
    public ResponseEntity<Void> approveRequest(@PathVariable("id") Long id) {

        RegistrationRequest request = requestService.findById(id);
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        request.setApproved(true);
        requestService.save(request);

        try {
            emailService.sendNotificaitionOfApprovedRegistrationRequest(request, id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/decline/{id}")
    public ResponseEntity<Void> declineRequest(@PathVariable("id") Long id) {

        RegistrationRequest request = requestService.findById(id);
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        request.setApproved(true);
        requestService.remove(id);

        try {
            emailService.sendNotificaitionOfDeclinedRegistrationRequest(request, id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = appUserService.findByEmail(loginDTO.getEmail());
        RegistrationRequest request = null;
        if(appUser != null) {
            request = requestService.findById(appUser.getId());
        }

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!appUser.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(request != null && !request.isApproved()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!appUser.getPassword().equals(loginDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new LoginResponseDTO(appUser), HttpStatus.OK);
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/requests")
    public ResponseEntity<Collection<AppUserSpecialDTO>> findAllRequests() {
        Collection<RegistrationRequest> requests = requestService.findAll();
        Collection<AppUserSpecialDTO> requestsDTOs = new ArrayList<>();
        for (RegistrationRequest request : requests) {
            if (!request.isApproved())
                requestsDTOs.add(new AppUserSpecialDTO(request));
        }
        return new ResponseEntity<>(requestsDTOs, HttpStatus.OK);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="/update")
    public ResponseEntity<AppUserDTO> update(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = appUserService.findOne(appUserDTO.getId());

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        appUser.setPassword(appUserDTO.getPassword());
        appUser.setName(appUserDTO.getName());
        appUser.setSurname(appUserDTO.getSurname());
        appUser.setAddress(appUserDTO.getAddress());
        appUser.setCity(appUserDTO.getCity());
        appUser.setCountry(appUserDTO.getCountry());
        appUser.setTelephone(appUserDTO.getTelephone());

        appUser = appUserService.save(appUser);
        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }
}
