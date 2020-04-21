package com.alarm.com.sensor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.rolling.helper.RenameUtil;

@RestController
public class SensorController {
	@Autowired
	private  SensorService sensorService;
	
	@RequestMapping(value = "/sensor", method = RequestMethod.GET)
	public List<Sensor> getAllSensorDetails() {
		return sensorService.getAll();
	}
	
	@RequestMapping(value = "/sensors/{id}", method = RequestMethod.GET)
	public Sensor getSensorDetailsById(@PathVariable int id) {
		return sensorService.getById(id);
	}
	
	@RequestMapping(value = "/sensors/save", method = RequestMethod.POST)
	public void newSensor(@RequestBody Sensor sensor) {
		sensorService.saveSensor(sensor);
	}
	
	@RequestMapping(value = "/sensors/update", method = RequestMethod.PUT)
	public void updateSensorDetails(@RequestBody Sensor sensor) {
		sensorService.updateSensor(sensor);
	}
	
	@RequestMapping(value = "/sensors/delete/{id}", method = RequestMethod.DELETE)
	public void deletSensorById(@PathVariable int id) {
		sensorService.deleteSensor(id);
	}
}
