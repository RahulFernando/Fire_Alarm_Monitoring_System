package com.alarm.com.sensor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.alarm.com.location.Location;

@Entity
@Table(name = "SENSOR")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SENSOR_ID")
	private int id;
	@Column(name = "CO2")
	private int co2;
	@Column(name = "SMOKELVL")
	private int smokeLvl;
	
	@OneToOne(mappedBy = "sensor")
	private Location location;
	
	public Sensor() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getSmokeLvl() {
		return smokeLvl;
	}

	public void setSmokeLvl(int smokeLvl) {
		this.smokeLvl = smokeLvl;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
