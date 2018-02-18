package net.mkengineering.studies.poi.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.mkengineering.studies.poi.GpsResponse;
import net.mkengineering.studies.poi.remote.POIFeign;
import net.mkengineering.studies.poi.remote.UIFeign;

@Component
public class CallbackRunner implements Runnable {

	private static UIFeign uiFeign;

	private static POIFeign feignClient;
	
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
	
	@Autowired
	public CallbackRunner(UIFeign uiFeign, POIFeign poiFeign) {
		//For Spring Context initialization.
		CallbackRunner.uiFeign = uiFeign;
		CallbackRunner.feignClient = poiFeign;
	}

	@Override
	public void run() {
		// Poor retry mechanism
		ResponseEntity<List<GpsResponse>> feignIn = null;
		for (int i = 0; i < 20; i++) {
			try {
				feignIn = feignClient.getPOIsAround(vin, longitude, latitude, cached, false);
			} catch (Exception e) {
				System.out.println("Attempt failed");
				// Retry...
			}
			if (feignIn != null)
				break;
			else {
				try {
					Thread.sleep(3000); // Retry after 3s.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (feignIn != null) {
			for (GpsResponse gpsResp : feignIn.getBody()) {
				try {
					Thread.sleep(2000); // To have a visible effect.
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
