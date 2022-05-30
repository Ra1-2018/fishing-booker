package com.isa.project.dto;

import com.isa.project.model.Room;

public class RoomDTO {
    private long id;
    private String roomCode;
    private long cottageId;
    private int numberOfBeds;

    public RoomDTO() {}

    public RoomDTO(long id, String roomCode, long cottageId, int numberOfBeds) {
        this.id = id;
        this.roomCode = roomCode;
        this.cottageId = cottageId;
        this.numberOfBeds = numberOfBeds;
    }

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.roomCode = room.getRoomCode();
        this.cottageId = room.getCottage().getId();
        this.numberOfBeds = room.getNumberOfBeds();
    }

    public long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public long getCottageId() {
        return cottageId;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }
}
