package com.epicproportionstour.maps.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epicproportionstour.maps.model.Geocodes;
import com.epicproportionstour.maps.service.GeocodeService;

@Controller
public class MapController {
	
	@Autowired
	GeocodeService geocodeService;

	@RequestMapping(value="/secured/mapsearch", method = RequestMethod.GET)
	public String showResult() {

		return "secured/search";
	}

	@RequestMapping("/secured/save")
	public ModelAndView saveGeocode(Model model,Geocodes geocodes,
			@RequestParam("geocode") String geocode) {

		System.out.println("In the MapController--the geocode is " + geocode);
		geocode = geocode.replace("(", "");
		geocode = geocode.replace(")", "");
		String[] gc = geocode.split(",");
		double latitude = Double.parseDouble(gc[0]);
		double longitude = Double.parseDouble(gc[1]);
		
		geocodes.setLatitude(latitude);
		geocodes.setLongitude(longitude);
		geocodes.setDate(new Date());;
		geocodeService.addGeocode(geocodes);
		
		System.out.println("back from the service and dao");
		
		ModelAndView mv = new ModelAndView("secured/geocodeSaved");
		//Map<String, Object> actualGeocodes = mv.getModel();
		//actualGeocodes.put("latitude", latitude);
		//actualGeocodes.put("longitude", longitude);
		//mv.addObject(actualGeocodes);
		System.out.println("The view name in model object is: "+ mv.getModel()+ " and the view is "+mv.getViewName());
		return mv;
	}
}
