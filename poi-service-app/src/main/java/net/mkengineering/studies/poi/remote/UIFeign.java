package net.mkengineering.studies.poi.remote;

import org.springframework.cloud.netflix.feign.FeignClient;

import net.mkengineering.studies.ui.service.UiServiceInterface;

@FeignClient(name="ui-service-app")
public interface UIFeign extends UiServiceInterface {

}
