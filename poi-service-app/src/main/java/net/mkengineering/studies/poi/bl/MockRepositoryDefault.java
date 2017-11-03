package net.mkengineering.studies.poi.bl;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import net.mkengineering.studies.poi.GpsResponse;

@Component
@ConditionalOnProperty(name="repository.location", havingValue="default")
public class MockRepositoryDefault implements POIRepository{

	@Override
	public List<GpsResponse> getPOIaround(String vin, Float latitude, Float longitude, Boolean cached) {
		// TODO Auto-generated method stub
		return null;
	}

}
