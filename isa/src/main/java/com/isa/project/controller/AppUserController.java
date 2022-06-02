package com.isa.project.controller;

import com.isa.project.dto.*;
import com.isa.project.model.*;
import com.isa.project.service.*;
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
    private DeletionRequestService deletionService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private CottageService cottageService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private ResponseToRegistrationRequestService responseService;

    @Autowired
    private ResponseToDeletionRequestService responseDeletionService;

    @Autowired
    private ResponseToComplaintService responseComplaintService;

    @Autowired
    private EmailService emailService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OwnerService ownerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO appUserDTO) {

        if (appUserDTO.getEmail() == null || appUserDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(appUserService.findByEmail(appUserDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AppUser appUser = new Client(appUserDTO.getId(), appUserDTO.getEmail(), passwordEncoder.encode(appUserDTO.getPassword()), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        appUser = appUserService.saveClient(appUser);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(null, token, appUser, new Date());
        verificationTokenService.save(verificationToken);

        try {
            emailService.sendNotificationAsync(appUser, token);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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

        String password = UUID.randomUUID().toString();
        AppUser appUser = new Administrator(appUserDTO.getId(), appUserDTO.getEmail(), passwordEncoder.encode(password), appUserDTO.getName(), appUserDTO.getSurname(), appUserDTO.getAddress(), appUserDTO.getCity(), appUserDTO.getCountry(), appUserDTO.getTelephone());
        appUser.setEnabled(true);
        appUser = appUserService.saveAdministrator(appUser);


        try {
            emailService.sendNotificationAdminReg(appUser, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @GetMapping("/activate/{token}")
    public RedirectView activate(@PathVariable("token") String token) {

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        //System.out.println(verificationToken.toString());
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approve/{id}")
    public ResponseEntity<Void> approveRequest(@PathVariable("id") Long id) {

        RegistrationRequest request = requestService.findById(id);
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AppUser user = request.getUser();
        request.setApproved(true);
        user.setEnabled(true);
        appUserService.save(user);
        requestService.save(request);

        try {
            emailService.sendNotificationOfApprovedRegistrationRequest(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/decline")
    public ResponseEntity<Void> declineRequest(@RequestBody ResponseToRegistrationRequestDTO responseRegistrationRequestDTO) {

        RegistrationRequest request = requestService.findById(responseRegistrationRequestDTO.getRequestID());
        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Administrator admin = (Administrator) appUserService.findOne(responseRegistrationRequestDTO.getUserID());
        ResponseToRegistrationRequest response = new ResponseToRegistrationRequest(null, admin, request, responseRegistrationRequestDTO.getExplanation());

        AppUser user = request.getUser();
        request.setApproved(false);
        user.setEnabled(false);
        response.setApproved(false);
        appUserService.save(user);
        requestService.remove(request.getId());
        responseService.save(response);

        try {
            emailService.sendNotificationOfDeclinedRegistrationRequest(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approveReview/{id}")
    public ResponseEntity<Void> approveReviewRequest(@PathVariable("id") Long id) {

        Review review = reviewService.findById(id);
        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        review.setApproved(true);
        reviewService.save(review);

        Long userID = 0L;
        Collection<Service> services = serviceService.findAll();
        for (Service service: services) {
            if(service.getServiceType().equals(ServiceType.ADVENTURE)){
                Adventure a = adventureService.findById(service.getId());
                userID = a.getInstructor().getId();
            } else if (service.getServiceType().equals(ServiceType.BOAT)) {
                Boat b = boatService.findById(service.getId());
                userID = b.getBoatOwner().getId();
            } else if (service.getServiceType().equals(ServiceType.COTTAGE)) {
                Cottage c = cottageService.findById(service.getId());
                userID = c.getCottageOwner().getId();
            }
        }
        AppUser user = appUserService.findOne(userID);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            emailService.sendNotificationOfApprovedReview(review, user);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/declineReview/{id}")
    public ResponseEntity<Void> declineReviewRequest(@PathVariable("id") Long id) {

        Review review = reviewService.findById(id);
        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reviewService.remove(review.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approveDeletion/{id}")
    public ResponseEntity<Void> approveDeletionRequest(@PathVariable("id") Long id) {

        DeletionRequest request = deletionService.findById(id);
        AppUser user = appUserService.findByEmail(request.getUserEmail());
        if(request == null || user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deletionService.remove(id);
        appUserService.remove(user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/declineDeletion")
    public ResponseEntity<Void> declineDeletionRequest(@RequestBody ResponseToDeletionRequestDTO responseDeletionRequestDTO) {

        DeletionRequest request = deletionService.findById(responseDeletionRequestDTO.getRequestID());
        Administrator admin = (Administrator) appUserService.findOne(responseDeletionRequestDTO.getUserID());
        ResponseToDeletionRequest response = new ResponseToDeletionRequest(null, admin, request, responseDeletionRequestDTO.getExplanation());

        AppUser user = appUserService.findByEmail(request.getUserEmail());
        if(request == null || user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        response.setApproved(false);
        deletionService.remove(request.getId());
        responseDeletionService.save(response);

        try {
            emailService.sendNotificationOfDeclinedDeletionRequest(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approveComplaint/{id}")
    public ResponseEntity<Void> approveComplaint(@PathVariable("id") Long id) {

        Complaint request = complaintService.findById(id);
        AppUser user = appUserService.findByEmail(request.getClient().getEmail());
        if(request == null || user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        request.setApproved(true);
        complaintService.save(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/declineComplaint")
    public ResponseEntity<Void> declineComplaint(@RequestBody ResponseToComplaintDTO responseToComplaintDTO) {

        Complaint request = complaintService.findById(responseToComplaintDTO.getRequestID());
        Administrator admin = (Administrator) appUserService.findOne(responseToComplaintDTO.getUserID());
        ResponseToComplaint response = new ResponseToComplaint(null, admin, request, responseToComplaintDTO.getContent());

        if(request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        response.setApproved(false);
        complaintService.remove(request.getId());
        responseComplaintService.save(response);

        try {
            emailService.sendNotificationOfDeclinedComplaint(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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

        Administrator admin = null;
        if(appUser.getAppUserType() == AppUserType.ADMIN) {
            admin = (Administrator) appUserService.findOne(appUser.getId());
            return new ResponseEntity<>(new LoginResponseDTO(appUser, userTokenState, admin.isFirstReg()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LoginResponseDTO(appUser, userTokenState, false), HttpStatus.OK);
        }
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/cottage-owners")
    public ResponseEntity<Collection<AppUserDTO>> findAllCottageOwners() {
        Collection<AppUser> appUsers = appUserService.findAll();
        Collection<AppUserDTO> appUserDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if(appUser.getAppUserType() == AppUserType.COTTAGE_OWNER) {
                appUserDTOS.add(new AppUserDTO(appUser));
            }
        }

        return new ResponseEntity<>(appUserDTOS, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/boat-owners")
    public ResponseEntity<Collection<AppUserDTO>> findAllBoatOwners() {
        Collection<AppUser> appUsers = appUserService.findAll();
        Collection<AppUserDTO> appUserDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if(appUser.getAppUserType() == AppUserType.BOAT_OWNER) {
                appUserDTOS.add(new AppUserDTO(appUser));
            }
        }

        return new ResponseEntity<>(appUserDTOS, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/instructors")
    public ResponseEntity<Collection<AppUserDTO>> findAllInstructors() {
        Collection<AppUser> appUsers = appUserService.findAll();
        Collection<AppUserDTO> appUserDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if(appUser.getAppUserType() == AppUserType.INSTRUCTOR) {
                appUserDTOS.add(new AppUserDTO(appUser));
            }
        }

        return new ResponseEntity<>(appUserDTOS, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/clients")
    public ResponseEntity<Collection<AppUserDTO>> findAllClients() {
        Collection<AppUser> appUsers = appUserService.findAll();
        Collection<AppUserDTO> appUserDTOS = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if(appUser.getAppUserType() == AppUserType.CLIENT) {
                appUserDTOS.add(new AppUserDTO(appUser));
            }
        }

        return new ResponseEntity<>(appUserDTOS, HttpStatus.OK);
    }

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/reviews")
    public ResponseEntity<Collection<ReviewDTO>> findAllReviews() {
        Collection<Review> reviews = reviewService.findAll();
        Collection<ReviewDTO> reviewsDTOs = new ArrayList<>();
        for (Review review : reviews) {
            if (!review.isApproved())
                reviewsDTOs.add(new ReviewDTO(review));
        }
        return new ResponseEntity<>(reviewsDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/deletionRequests")
    public ResponseEntity<Collection<DeletionRequestDTO>> findAllDeletionRequests() {
        Collection<DeletionRequest> requests = deletionService.findAll();
        Collection<DeletionRequestDTO> requestsDTOs = new ArrayList<>();
        for (DeletionRequest request : requests) {
            if (!request.isApproved())
                requestsDTOs.add(new DeletionRequestDTO(request));
        }
        return new ResponseEntity<>(requestsDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/complaints")
    public ResponseEntity<Collection<ComplaintDTO>> findAllComplaints() {
        Collection<Complaint> complaints = complaintService.findAll();
        Collection<ComplaintDTO> complaintsDTOs = new ArrayList<>();
        for (Complaint complaint : complaints) {
            if (!complaint.isApproved())
                complaintsDTOs.add(new ComplaintDTO(complaint));
        }
        return new ResponseEntity<>(complaintsDTOs, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('USER')")
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login-new-admin")
    public ResponseEntity<AppUserDTO> passwordUpdateNewAdmin(@RequestBody AppUserDTO appUserDTO) {

        Administrator appUser = (Administrator) appUserService.findOne(appUserDTO.getId());

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUser.setFirstReg(false);
        appUser = (Administrator) appUserService.saveAdministrator(appUser);
        return new ResponseEntity<>(new AppUserDTO(appUser), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/changePassword")
    public ResponseEntity<ChangePasswordDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        AppUser appUser = appUserService.findOne(changePasswordDTO.getUserID());
        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), appUser.getPassword())){
            appUser.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            appUserService.save(appUser);
            return new ResponseEntity<>(new ChangePasswordDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppUserDTO>> findOwnerClients(@PathVariable("id") long id) {
        AppUser appUser = appUserService.findOne(id);

        if(appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Collection<Client> clients = ownerService.getOwnerClients(appUser);

        Collection<AppUserDTO> clientDTOS = new ArrayList<>();

        for (Client client : clients) {
            clientDTOS.add(new AppUserDTO(client));
        }
        return new ResponseEntity<>(clientDTOS, HttpStatus.OK);
    }
}
