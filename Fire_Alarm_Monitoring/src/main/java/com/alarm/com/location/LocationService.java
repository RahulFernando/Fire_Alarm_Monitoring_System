package com.alarm.com.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	// return location details
	public List<Location> getAllLocation() {
		List<Location> list = new ArrayList<>();
		locationRepository.findAll().forEach(list::add); // find all the data related to Location
		return list;
	}
	
	// get location details by id
	public Location getLocation(int id) {
		// a container object which may or may not contain a non-null value
		Optional<Location> optionalLocation = locationRepository.findById(id);
		if (optionalLocation.isPresent()) { // check optionalLocation has value  then return it
			return optionalLocation.get();
		}
		
		return null;
	}
	
	// save 
	public void newLocation(Location location) {
		// save new location
		locationRepository.save(location);
	}
	
	// update 
	public void updateLocation(Location location) {
		// see the id and update particular location
		locationRepository.save(location);
	}
	
	// delete 
	public void deleteLocation(int id) {
		// delete location by id
		locationRepository.deleteById(id);
	}
}
