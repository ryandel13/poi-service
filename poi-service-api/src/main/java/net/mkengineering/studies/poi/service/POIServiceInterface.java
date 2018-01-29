package net.mkengineering.studies.poi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mkengineering.studies.poi.DataResponse;
import net.mkengineering.studies.poi.GpsResponse;


public interface POIServiceInterface {
public final String CONTEXT = "poi";
	
	@RequestMapping(value = CONTEXT + "/{vin}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<GpsResponse>> getPOIsAround(
			@PathVariable("vin") String vin, 
			@RequestParam("longitude") Float longitude, 
			@RequestParam("latitude") Float latitude, 
			@RequestParam("cached") Boolean cached, 
			@RequestParam("callback") Boolean callback);

}
