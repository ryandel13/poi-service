package net.mkengineering.studies.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableAutoConfiguration
@EnableFeignClients
@EnableCaching
public class StaticDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticDataServiceApplication.class, args);
	}
}
