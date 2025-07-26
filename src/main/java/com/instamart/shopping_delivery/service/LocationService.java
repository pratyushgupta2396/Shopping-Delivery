package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.models.Location;
import com.instamart.shopping_delivery.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/*
This service class contains all the logics related to locations.
 */
@Service
@Slf4j
public class LocationService {

    LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }


    /*
    Work of this function is to save the location object in the location table.
     */
    public Location createLocation(Location location){
        // Now we want to save the location in the location table
        // That means we require location repository whihc will save location in the location table
        location.setCreatedAt(LocalDateTime.now());
        location.setUpdatedAt(LocalDateTime.now());
        return locationRepository.save(location);
    }
}