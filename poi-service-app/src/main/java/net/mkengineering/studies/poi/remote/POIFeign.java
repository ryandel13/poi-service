package net.mkengineering.studies.poi.remote;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mkengineering.studies.poi.GpsResponse;
import net.mkengineering.studies.poi.service.POIServiceInterface;

@FeignClient(name="poi-service", url="ryandel.selfhost.me:8808")
public interface POIFeign extends POIServiceInterface{

}