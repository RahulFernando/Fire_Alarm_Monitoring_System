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
	public List<Location> getSensorDetails() {
		return locationService.getAllSensors();
	}
	
	@RequestMapping(value = "/location/{id}")
	public Location getSensorById(@PathVariable int id) {
		return locationService.getSensor(id);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public void saveSensor(@RequestBody Location sensor) {
		locationService.newSensor(sensor);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.PUT)
	public void updateSensor(@RequestBody Location sensor) {
		locationService.updateSensor(sensor);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.DELETE)
	public void deleteSensor(@PathVariable int id) {
		locationService.deleteSensor(id);
	}
}
