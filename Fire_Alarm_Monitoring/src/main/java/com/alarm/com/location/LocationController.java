package com.alarm.com.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public List<Location> getLocationDetails() {
		return locationService.getAllLocation();
	}
	
	@RequestMapping(value = "/location/{id}")
	public Location getLocationById(@PathVariable int id) {
		return locationService.getLocation(id);
	}
	
	@RequestMapping(value = "/location/save", method = RequestMethod.POST)
	public void saveLocation(@RequestBody Location location) {
		locationService.newLocation(location);
	}
	
	@RequestMapping(value = "/location/update", method = RequestMethod.PUT)
	public void updateLocation(@RequestBody Location location) {
		locationService.updateLocation(location);
	}
	
	@RequestMapping(value = "/location/delete/{id}", method = RequestMethod.DELETE)
	public void deleteLocation(@PathVariable int id) {
		locationService.deleteLocation(id);
	}
}
