package com.isa.project.controller;

import com.isa.project.model.Cottage;
import com.isa.project.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cottages")
public class CottageController {

    @Autowired
    private CottageService cottageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Cottage>> getCottages() {
        Collection<Cottage> cottages = cottageService.findAll();
        return new ResponseEntity<Collection<Cottage>>(cottages, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cottage> getCottage(@PathVariable("id") Long id) {
        Cottage cottage = cottageService.findById(id);

        if (cottage == null) {
            return new ResponseEntity<Cottage>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cottage>(cottage, HttpStatus.OK);
    }
}
