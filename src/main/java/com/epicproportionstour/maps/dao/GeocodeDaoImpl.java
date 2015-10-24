package com.epicproportionstour.maps.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.maps.model.Geocodes;

@Repository
public class GeocodeDaoImpl implements GeocodeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	/*@Autowired
	public GeocodeDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		
	}
	*/
	@Transactional
	@Override
	public void addGeocode(Geocodes geocodes) {

		Session session = sessionFactory.getCurrentSession();
		
		System.out.println("Inside GeocodeDaoImpl " + geocodes);

		session.save(geocodes);
		//session.getTransaction().commit();
		System.out.println("Insert geocodes successfully");
		//		session.close();

	}

}
