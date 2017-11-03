package net.mkengineering.studies.poi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:static.yml")
@ConfigurationProperties("static")
public class StaticConfig {

	private String vin;
	
	public String getVin() {
		return vin;
	}
}
