package com.isa.project.controller;


import com.isa.project.model.Instructor;
import com.isa.project.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Instructor>> getInstructors() {
        Collection<Instructor> instructors = instructorService.findAll();
        return new ResponseEntity<Collection<Instructor>>(instructors, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Instructor> getInstructor(@PathVariable("id") Long id) {
        Instructor instructor = instructorService.findOne(id);

        if(instructor == null) {
            return new ResponseEntity<Instructor>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Instructor>(instructor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Instructor> deleteInstructor(@PathVariable("id") Long id) {
        instructorService.delete(id);
        return new ResponseEntity<Instructor>(HttpStatus.NO_CONTENT);
    }
}
