package com.epicproportionstour.maps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "Geocodes")
public class Geocodes {
	@Id
	@Column(name = "gc_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name = "gc_latitude")
	private double latitude;
	@Column(name = "gc_longitude")
	private double longitude;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isToured() {
		return toured;
	}

	public void setToured(boolean toured) {
		this.toured = toured;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "gc_date_created")
	private Date date;
	@Column(name = "gc_toured")
	private boolean toured;
	@Column(name = "gc_location")
	private String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Geocodes [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}

}
