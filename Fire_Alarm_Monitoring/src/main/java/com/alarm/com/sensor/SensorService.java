package com.alarm.com.sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
	@Autowired
	private SensorRepository sensorRepository;
	
	// return all sensor details
	public List<Sensor> getAll() {
		List<Sensor> list = new ArrayList<Sensor>();
		sensorRepository.findAll().forEach(list::add);  // find all the data related to Sensor
		return list;
	}
	
	// return sensor by id
	public Sensor getById(int id) {
		Optional<Sensor> optional = sensorRepository.findById(id); 
		if (optional.isPresent()) { // check optionalLocation has value  then return it
			return optional.get();
		}
		
		return null;
	}
	
	// save sensor details
	public void saveSensor(Sensor sensor) {
		// save new sensor
		sensorRepository.save(sensor);
	}
	
	// update sensor details
	public void updateSensor(Sensor sensor) {
		// update 
		sensorRepository.save(sensor);
	}
	
	// delete sensor by id
	public void deleteSensor(int id) {
		// delete
		sensorRepository.deleteById(id);
	}
}
