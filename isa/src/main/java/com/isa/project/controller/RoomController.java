package com.isa.project.controller;

import com.isa.project.dto.ActionDTO;
import com.isa.project.dto.RoomDTO;
import com.isa.project.model.Action;
import com.isa.project.model.Cottage;
import com.isa.project.model.Room;
import com.isa.project.model.Service;
import com.isa.project.service.CottageService;
import com.isa.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CottageService cottageService;

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {

        Cottage cottage = cottageService.findById(roomDTO.getCottageId());

        if(cottage == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        Room room = new Room();
        room.setRoomCode(roomDTO.getRoomCode());
        room.setCottage(cottage);
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());

        room = roomService.save(room);

        return new ResponseEntity<>(new RoomDTO(room), HttpStatus.OK);
    }

    @GetMapping(value = "cottage/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RoomDTO>> findByCottage(@PathVariable("id") Long id) {
        Cottage cottage = cottageService.findById(id);
        if(cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Collection<Room> rooms = roomService.findByCottage(cottage);
        Collection<RoomDTO> roomDTOS = new ArrayList<>();
        for(Room room : rooms) {
            roomDTOS.add(new RoomDTO((room)));
        }
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }
}
