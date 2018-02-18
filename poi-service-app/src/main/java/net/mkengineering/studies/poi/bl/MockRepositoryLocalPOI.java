package net.mkengineering.studies.poi.bl;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
	@Autowired
	private UIFeign uiFeign;
	
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
	
	private class CallbackRunner implements Runnable {
		
		private String vin;
		
		private Float longitude;
		
		private Float latitude;
		
		private Boolean cached;
		
		private ObjectMapper oM = new ObjectMapper();
		
		public CallbackRunner(String vin, Float latitude, Float longitude, Boolean cached) {
			this.vin = vin;
			this.longitude = longitude;
			this.latitude = latitude;
			this.cached = cached;
		}
		
		@Override
		public void run() {
			//Poor retry mechanism
			ResponseEntity<List<GpsResponse>> feignIn = null;
			for(int i = 0; i<20; i++) {
				try {
					feignIn = feignClient.getPOIsAround(vin, longitude, latitude, cached, false);
				} catch (Exception e) {
					System.out.println("Attempt failed");
					//Retry...
				}
				if(feignIn != null) break;
				else { 
					try {
						Thread.sleep(3000); //Retry after 3s.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if(feignIn != null) {
				for(GpsResponse gpsResp : feignIn.getBody()) {
					try {
						Thread.sleep(2000); //To have a visible effect.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					responseForwarder("localhost", 8000, gpsResp);
				}
			}
		}
		
		private void responseForwarder(String inurl, int port, GpsResponse respo) {
			try {
				ObjectNode output = oM.convertValue(respo, ObjectNode.class);
				uiFeign.pushGps(output);
				
			} catch (Exception ex) {
				System.out.println("Error occured");
				ex.printStackTrace();
			}
		}
		
	}
	
}
