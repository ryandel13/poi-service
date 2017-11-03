package net.mkengineering.studies.poi.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import net.mkengineering.studies.poi.GpsResponse;

@Component
@ConditionalOnProperty(name="repository.location", havingValue="memory")
public class MockRepositoryLocalPOI implements POIRepository {
	
	GpsResponse poi1 = new GpsResponse(20f, 60f, "local", "Schloss", "...");
	GpsResponse poi2 = new GpsResponse(320f, 260f, "local", "BlaBla", "...");
	GpsResponse poi3 = new GpsResponse(200f, 10f, "local", "BlaBla2", "...");
	
	@Override
	public List<GpsResponse> getPOIaround(String vin, Float latitude, Float longitude, Boolean cached) {
		List<GpsResponse> out = new ArrayList<>();
		out.add(poi1);
		out.add(poi2);
		out.add(poi3);
		
		return out;
	}
	
	
	
}
