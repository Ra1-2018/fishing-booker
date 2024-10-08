package com.isa.project.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Service {
    @Id
    @SequenceGenerator(name = "serviceSeqGen", sequenceName = "serviceSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceSeqGen")
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String behaviorRules;
    @Column
    private double pricePerDay;
    @Column
    private ServiceType serviceType;
    @Column
    private int maxNumberOfPeople;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TimeRange> freePeriods = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AdditionalService> additionalServices = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Action> actions = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    private Set<Client> subscribedClients;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<>();

    @OneToOne(mappedBy = "service")
    private Location location;

    @Version
    private Integer version;

    @Column
    private Date lastUpdateDate;

    public Service() {}

    public Service(long id, String name, String description, String behaviorRules, double pricePerDay, ServiceType serviceType, Set<Reservation> reservations, Set<TimeRange> freePeriods, int maxNumberOfPeople, Set<AdditionalService> additionalServices, Set<Action> actions, Set<Review> reviews, Set<Client> subscribedClients, Set<Complaint> complaints, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.behaviorRules = behaviorRules;
        this.pricePerDay = pricePerDay;
        this.serviceType = serviceType;
        this.reservations = reservations;
        this.freePeriods = freePeriods;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.additionalServices = additionalServices;
        this.actions = actions;
        this.reviews = reviews;
        this.subscribedClients = subscribedClients;
        this.complaints = complaints;
        this.lastUpdateDate = new Date();
        this.images =  new HashSet<>();
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBehaviorRules() {
        return behaviorRules;
    }

    public void setBehaviorRules(String behaviorRules) {
        this.behaviorRules = behaviorRules;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<TimeRange> getAllPeriods() {
        return freePeriods;
    }

    public Set<TimeRange> getFreePeriods() {
        Set<TimeRange> freePeriods1 = new HashSet<TimeRange>();
        for (TimeRange freePeriod:  getAllPeriods()) {
            if(freePeriod.isAvailable()){
                freePeriods1.add(freePeriod);
            }
        }
        return freePeriods1;
    }

    public Set<TimeRange> getUnavailablePeriods() {
        Set<TimeRange> periods = new HashSet<TimeRange>();
        for (TimeRange period:  getAllPeriods()) {
            if(!period.isAvailable()){
                periods.add(period);
            }
        }
        return periods;
    }

    public void setFreePeriods(Set<TimeRange> freePeriods) { this.freePeriods = freePeriods; }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public void addFreePeriod(TimeRange freePeriod) {
        freePeriods.add(freePeriod);
        freePeriod.setService(this);
    }

    public void addUnavailablePeriod(TimeRange period) {
        period.setAvailable(false);
        freePeriods.add(period);
        period.setService(this);
    }

    public void removeFreePeriod(TimeRange freePeriod) {
        for(TimeRange fp: freePeriods) {
            if(fp.getId().equals(freePeriod.getId())) {
                freePeriods.remove(fp);
                break;
            }
        }
        freePeriod.setService(null);
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public void addAdditionalService(AdditionalService additionalService) {
        additionalServices.add(additionalService);
        additionalService.setService(this);
    }

    public void removeAdditionalService(AdditionalService additionalService) {
        additionalServices.remove(additionalService);
        additionalService.setService(null);
    }

    public void addAction(Action action) {
        actions.add(action);
        action.setService(this);
    }

    public void removeAction(Action action) {
        actions.remove(action);
        action.setService(null);
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Client> getSubscribedClients() {
        return subscribedClients;
    }

    public void setSubscribedClients(Set<Client> subscribedClients) {
        this.subscribedClients = subscribedClients;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    public double getAverageGrade() {
        if(reviews.size() == 0)
            return 0;
        double sum = 0;
        for(Review review: reviews) {
            sum += review.getGrade();
        }
        return sum/reviews.size();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Set<Image> getImages() { return images; }

    public void setImages(Set<Image> images) { this.images = images; }
}
