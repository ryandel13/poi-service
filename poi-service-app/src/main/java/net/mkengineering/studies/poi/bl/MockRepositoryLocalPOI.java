package net.mkengineering.studies.poi.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.mkengineering.studies.poi.GpsResponse;
import net.mkengineering.studies.poi.remote.POIFeign;
import net.mkengineering.studies.poi.remote.UIFeign;

@Component
@ConditionalOnProperty(name="repository.location", havingValue="memory")
public class MockRepositoryLocalPOI implements POIRepository {
	
	GpsResponse poi1 = new GpsResponse(20f, 60f, "local", "Schloss", "...");
	GpsResponse poi2 = new GpsResponse(320f, 260f, "local", "BlaBla", "...");
	GpsResponse poi3 = new GpsResponse(200f, 10f, "local", "BlaBla2", "...");
	
	@Autowired
	private POIFeign feignClient;
	
	@Override
	public List<GpsResponse> getPOIaround(String vin, Float latitude, Float longitude, Boolean cached, Boolean callback) {
		System.out.println("Processing POI for vin " + vin);
		List<GpsResponse> out = new ArrayList<GpsResponse>();
		if(callback) {
			Thread t = new Thread(new CallbackRunner(vin, latitude, longitude, cached));
			t.start();
		} else {
			ResponseEntity<List<GpsResponse>> feignIn = feignClient.getPOIsAround(vin, longitude, latitude, cached, false);
			out = feignIn.getBody();
		}
		
		out.add(poi1);
		out.add(poi2);
		out.add(poi3);
		
		return out;
	}	
}
