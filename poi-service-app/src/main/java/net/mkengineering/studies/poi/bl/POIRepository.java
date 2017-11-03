package net.mkengineering.studies.poi.bl;

import java.util.List;

import net.mkengineering.studies.poi.GpsResponse;

public interface POIRepository {

	public List<GpsResponse> getPOIaround(String vin, Float latitude, Float longitude, Boolean cached);

}
