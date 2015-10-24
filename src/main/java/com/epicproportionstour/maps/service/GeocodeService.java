package com.epicproportionstour.maps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicproportionstour.maps.dao.GeocodeDao;
import com.epicproportionstour.maps.dao.GeocodeDaoImpl;
import com.epicproportionstour.maps.model.Geocodes;

@Service
public class GeocodeService{

	@Autowired
	private GeocodeDao geocodeDao;

	
	public void addGeocode(Geocodes geocodes) {

		System.out.println("Inside GeocodeService "+ geocodes);
		geocodeDao.addGeocode(geocodes);
	}

}
