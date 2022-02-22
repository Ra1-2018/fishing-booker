package com.isa.project.controller;

import com.isa.project.dto.ActionDTO;
import com.isa.project.model.Action;
import com.isa.project.model.Service;
import com.isa.project.service.ActionService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/actions")
public class ActionController {
    @Autowired
    private ActionService actionService;

    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "service/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ActionDTO>> findByService(@PathVariable("id") Long id) {
        Service service = serviceService.findById(id);
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Action> actions = actionService.findByService(service);
        Collection<ActionDTO> actionDTOS = new ArrayList<>();
        for(Action action : actions) {
            actionDTOS.add(new ActionDTO((action)));
        }
        return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
    }
}
