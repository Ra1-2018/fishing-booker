package com.isa.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adventure extends Service{
    @Column
    private String instructorBiography;
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


    public Adventure(long id, String name, String description, String behaviorRules, double pricePerDay, String instructorBiography, Set<Reservation> reservations, String fishingGear, ReservationCancellation cancellation, Instructor instructor, Set<TimeRange> freeReservations, int maxNumberOfPeople, Set<AdditionalService> additionalServices, Set<Action> actions, Set<Review> reviews, Set<Client> subscribedClients, Set<Complaint> complaints, Location location) {
        super(id, name, description, behaviorRules, pricePerDay, ServiceType.ADVENTURE, reservations, freeReservations, maxNumberOfPeople, additionalServices, actions, reviews, subscribedClients, complaints, location);
        this.instructorBiography = instructorBiography;
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
