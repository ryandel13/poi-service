package net.mkengineering.studies.poi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.mkengineering.studies.poi.GpsResponse;
import net.mkengineering.studies.poi.bl.POIRepository;

@RestController
public class POIServiceImpl implements POIServiceInterface {

	@Autowired
	private POIRepository sdsRepo;

	@Override
	public ResponseEntity<List<GpsResponse>> getPOIsAround(@PathVariable String vin, Float longitude, Float latitude, Boolean cached) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<GpsResponse>>(sdsRepo.getPOIaround(vin, latitude, longitude, cached), HttpStatus.OK);
	}
	
}
