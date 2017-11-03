package net.mkengineering.studies.poi;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataResponse {

	List<GpsResponse> values = new ArrayList<>();
	
}
