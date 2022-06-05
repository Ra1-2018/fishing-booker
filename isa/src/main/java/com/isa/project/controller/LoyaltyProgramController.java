package com.isa.project.controller;


import com.isa.project.model.LoyaltyProgram;
import com.isa.project.repository.LoyaltyProgramRepository;
import com.isa.project.service.LoyaltyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loyalty")
public class LoyaltyProgramController {

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public LoyaltyProgram getLoyaltyProgram(){
        return this.loyaltyProgramRepository.findAll().get(0);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateLoyalty(@RequestBody LoyaltyProgram program){

        if(program.getPercentForBronze() < 0 || program.getPercentForBronze() > 100 ||
                program.getPercentForSilver() < 0 || program.getPercentForSilver() > 100 ||
                program.getPercentForGold() < 0 || program.getPercentForGold() > 100 ||
                program.getPointsForBronze() < 0 || program.getPointsForSilver() < 0 ||
                program.getPointsForGold() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.loyaltyProgramService.updateLoyaltyProgram(program);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
