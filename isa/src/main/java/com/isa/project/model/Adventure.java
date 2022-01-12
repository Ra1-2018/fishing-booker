package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adventure extends Service{
    @Column
    private String instructorBiography;
    @Column
    private int maxPeople;
    @Column
    private String fishingGear;
    @Column
    private ReservationCancellation cancellation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Adventure() {
        super();
    }


    public Adventure(long id, String name, String description, String behaviorRules, String priceList, String address, String instructorBiography, int maxPeople, Set<Reservation> reservations, String fishingGear, ReservationCancellation cancellation, Instructor instructor, Set<TimeRange> freeReservations) {
        super(id, name, description, behaviorRules, priceList, address, ServiceType.ADVENTURE, reservations, freeReservations);
        this.instructorBiography = instructorBiography;
        this.maxPeople = maxPeople;
        this.fishingGear = fishingGear;
        this.cancellation = cancellation;
        this.instructor = instructor;
    }

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public void setInstructorBiography(String instructorBiography) {
        this.instructorBiography = instructorBiography;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getFishingGear() {
        return fishingGear;
    }

    public void setFishingGear(String fishingGear) {
        this.fishingGear = fishingGear;
    }

    public ReservationCancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(ReservationCancellation cancellation) {
        this.cancellation = cancellation;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
