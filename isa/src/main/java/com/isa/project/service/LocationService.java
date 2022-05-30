package com.isa.project.service;

import com.isa.project.model.Location;
import com.isa.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location location) { return  locationRepository.save(location); }

}
