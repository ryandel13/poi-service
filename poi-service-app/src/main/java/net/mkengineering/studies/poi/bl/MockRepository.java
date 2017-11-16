package net.mkengineering.studies.poi.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import net.mkengineering.studies.poi.GpsResponse;

@Component
@ConditionalOnProperty(name="repository.location", havingValue="database")
public class MockRepository implements POIRepository{

	GpsResponse poi1 = new GpsResponse(40f, 60f, "remote", "Schloss2", "...");
	GpsResponse poi2 = new GpsResponse(340f, 260f, "remote", "Remote", "...");
	GpsResponse poi3 = new GpsResponse(220f, 10f, "remote", "Remote2", "...");
	
	@Override
	public List<GpsResponse> getPOIaround(String vin, Float latitude, Float longitude, Boolean cached) {
		List<GpsResponse> out = new ArrayList<>();
		out.add(poi1);
		out.add(poi2);
		out.add(poi3);
		
		return out;
	}
}
