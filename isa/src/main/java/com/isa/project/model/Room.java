package com.isa.project.model;

import com.isa.project.dto.RoomDTO;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roomCode;

    @Column
    private int numberOfBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

    public Room() {}

    public Room(Long id, String roomCode, int numberOfBeds, Cottage cottage) {
        this.id = id;
        this.roomCode = roomCode;
        this.numberOfBeds = numberOfBeds;
        this.cottage = cottage;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRoomCode() { return roomCode; }

    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }

    public int getNumberOfBeds() { return numberOfBeds; }

    public void setNumberOfBeds(int numberOfBeds) { this.numberOfBeds = numberOfBeds; }

    public Cottage getCottage() { return cottage; }

    public void setCottage(Cottage cottage) { this.cottage = cottage; }
}
