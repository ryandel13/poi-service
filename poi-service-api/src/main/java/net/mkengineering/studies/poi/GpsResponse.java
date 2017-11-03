package net.mkengineering.studies.poi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GpsResponse {

	private Float longitude;
	
	private Float latitude;
	
	private String marker;
	
	private String name;
	
	private String description;
	
}

