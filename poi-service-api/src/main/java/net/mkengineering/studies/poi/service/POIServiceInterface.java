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
	public ResponseEntity<List<GpsResponse>> getPOIsAround(@PathVariable String vin, @RequestParam Float longitude, @RequestParam Float latitude, @RequestParam Boolean cached);

	/*
	@RequestMapping(value = CONTEXT + "/echo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> echo();
	
	@RequestMapping(value = CONTEXT + "/{vin}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DataResponse> getAllDataForVin(@PathVariable("vin") String vin);
	
	@RequestMapping(value = CONTEXT + "/{vin}/{attribute}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DataResponse> getAttributeForVin(@PathVariable("vin") String vin, @PathVariable("attribute") String attribute);

	@RequestMapping(value = CONTEXT + "/", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> evictCache();
	*/
}
