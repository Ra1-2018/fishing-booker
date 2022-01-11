package com.isa.project.controller;

import com.isa.project.dto.*;
import com.isa.project.model.*;
import com.isa.project.service.AppUserService;
import com.isa.project.service.RegistrationRequestService;
import com.isa.project.util.TokenUtils;
import com.isa.project.verification.EmailService;
import com.isa.project.verification.VerificationToken;
import com.isa.project.verification.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;


@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RegistrationRequestService requestService;

    @Autowired
    private EmailService emailService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO appUserDTO) {

        if (appUserDTO.getEmail() == null || appUserDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = new Client(appUserDTO.getId(), appUserDTO.getEmail(), passwordEncoder.encode(appUserDTO.getPassword()), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone(), new HashSet<>());
        appUser = appUserService.saveClient(appUser);

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/special")
    public ResponseEntity<AppUserDTO> saveSpecialUser(@RequestBody AppUserSpecialDTO appUserSpecialDTO) {

        if (appUserSpecialDTO.getEmail() == null || appUserSpecialDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserSpecialDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (appUserSpecialDTO.getAppUserType() == AppUserType.BOAT_OWNER) {
            AppUser boatOwner = new BoatOwner(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), passwordEncoder.encode(appUserSpecialDTO.getPassword()) , appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            boatOwner = appUserService.saveBoatOwner(boatOwner);

            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), boatOwner);
            requestService.save(request);
        }
        else if (appUserSpecialDTO.getAppUserType() == AppUserType.COTTAGE_OWNER) {
            AppUser cottageOwner = new CottageOwner(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), passwordEncoder.encode(appUserSpecialDTO.getPassword()), appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            cottageOwner = appUserService.saveCottageOwner(cottageOwner);

            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), cottageOwner);
            requestService.save(request);
        }
        else if(appUserSpecialDTO.getAppUserType() == AppUserType.INSTRUCTOR) {
            AppUser instructor = new Instructor(appUserSpecialDTO.getId(), appUserSpecialDTO.getEmail(), passwordEncoder.encode(appUserSpecialDTO.getPassword()), appUserSpecialDTO.getName(), appUserSpecialDTO.getSurname(), appUserSpecialDTO.getAddress(), appUserSpecialDTO.getCity(), appUserSpecialDTO.getCountry(), appUserSpecialDTO.getTelephone());
            instructor = appUserService.saveInstructor(instructor);

            RegistrationRequest request = new RegistrationRequest(null, appUserSpecialDTO.getExplanation(), instructor);
            requestService.save(request);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "admin")
    public ResponseEntity<AppUserDTO> saveAdministrator(@RequestBody AppUserDTO appUserDTO) {

        if (appUserDTO.getEmail() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String password = "sdh4ny7kid93nd";
        AppUser appUser = new Administrator(appUserDTO.getId(), appUserDTO.getEmail(), passwordEncoder.encode(password), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone());
        appUser.setEnabled(true);
        appUser = appUserService.saveAdministrator(appUser);


        try {
            emailService.sendNotificaitionAdminReg(appUser, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

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

    @GetMapping("/approve/{id}")
    public ResponseEntity<Void> approveRequest(@PathVariable("id") Long id) {

        RegistrationRequest request = requestService.findById(id);
        AppUser user = request.getUser();
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        request.setApproved(true);
        user.setEnabled(true);
        appUserService.save(user);
        requestService.save(request);

        try {
            emailService.sendNotificaitionOfApprovedRegistrationRequest(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Void> declineRequest(@PathVariable("id") Long id) {

        RegistrationRequest request = requestService.findById(id);
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        request.setApproved(false);
        requestService.remove(id);

        try {
            emailService.sendNotificaitionOfDeclinedRegistrationRequest(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(appUser.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        UserTokenState userTokenState = new UserTokenState(jwt, expiresIn);

        return new ResponseEntity<>(new LoginResponseDTO(appUser, userTokenState), HttpStatus.OK);
    }

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
    public ResponseEntity<Collection<RegistrationRequestDTO>> findAllRequests() {
        Collection<RegistrationRequest> requests = requestService.findAll();
        Collection<RegistrationRequestDTO> requestsDTOs = new ArrayList<>();
        for (RegistrationRequest request : requests) {
            if (!request.isApproved())
                requestsDTOs.add(new RegistrationRequestDTO(request));
        }
        return new ResponseEntity<>(requestsDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> findOne(@PathVariable("id") long id) {
        AppUser appUser = appUserService.findOne(id);

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") long id) {
        AppUser appUser = appUserService.findOne(id);
        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appUserService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/update")
    public ResponseEntity<AppUserDTO> update(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = appUserService.findOne(appUserDTO.getId());

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //appUser.setPassword(appUserDTO.getPassword());
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
