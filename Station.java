package com.JovicaIgnjatic;

import java.io.Serializable;
import java.time.LocalTime;


public class Station implements Serializable {
	
	private static final long serialVersionUID = 3985157571659838445L;
	private String name;
	private int id;
	private LocalTime arrival;
	private LocalTime departure;
	public Station(String name, int id) {
		this.name = name;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setArrival(int hour, int minute) {
		this.arrival = LocalTime.of(hour,minute);
	}

	public void setDeparture(int hour, int minute) {
		this.departure = LocalTime.of(hour,minute);
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	@Override
	public String toString() {
		return
				"name='" + name + '\'' +
				", id=" + id +
				", arrival=" + arrival +
				", departure=" + departure ;
	}
}
