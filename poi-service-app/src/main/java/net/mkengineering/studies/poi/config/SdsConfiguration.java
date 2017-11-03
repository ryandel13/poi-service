package net.mkengineering.studies.poi.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.mkengineering.studies.poi.bl.MockRepositoryLocalPOI;
import net.mkengineering.studies.poi.bl.MockRepositoryDefault;
import net.mkengineering.studies.poi.bl.POIRepository;

@Configuration
public class SdsConfiguration {
	
	@Bean
	@ConditionalOnMissingBean(POIRepository.class)
	public POIRepository myBeanForOthers() {
		System.out.println("LOADING FALLBACK BEAN");
		return new MockRepositoryDefault();
	}
}
