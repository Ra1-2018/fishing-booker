package com.isa.project.model;

public class Cottage {

    private long id;
    private String name;
    private String address;
    private String description;
    private int roomsTotalNumber;
    private String behaviorRules;

    public Cottage(long id, String name, String address, String description, int roomsTotalNumber, String behaviorRules) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomsTotalNumber = roomsTotalNumber;
        this.behaviorRules = behaviorRules;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomsTotalNumber() {
        return roomsTotalNumber;
    }

    public void setRoomsTotalNumber(int roomsTotalNumber) {
        this.roomsTotalNumber = roomsTotalNumber;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }
}
