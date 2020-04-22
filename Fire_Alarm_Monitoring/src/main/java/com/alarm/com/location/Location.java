package com.alarm.com.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOCATION_ID")
	private int id;
	@Column(name = "FLOOR_NO")
	private int floor_no;
	@Column(name = "ROOM_NO")
	private int  room_no;
	@Column(name = "CO2")
	private int co2 = 1;
	@Column(name = "SMOKELVL")
	private int smokeLvl = 1;
	@Column(name = "STATUS")
	private String status;
	
	public Location() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
