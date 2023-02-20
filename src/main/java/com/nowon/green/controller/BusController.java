package com.nowon.green.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.green.domain.dto.bus.BusInfo;
import com.nowon.green.domain.dto.bus.station.StationByRoute;
import com.nowon.green.utils.OpenApi;

@Controller
public class BusController {

	@GetMapping("/common/mybus")
	public String busInfo() {
		return "bus/info";
	}
	
	@Value("${busKey}")
	private String busKey;
	
	//ajax
	//
	@GetMapping("/common/mybus/{busNo}")
	public String busInfo(Model model, @PathVariable String busNo) throws IOException {
		//String busNo="2312";
		String apiURL ="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"
				+ "?serviceKey="+busKey
				+ "&resultType=json"
				+ "&strSrch="+busNo;
		
		String response=OpenApi.get(apiURL);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>");
		//System.out.println(response);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>");
		
		ObjectMapper mapper = new ObjectMapper();
		BusInfo busInfo = mapper.readValue(response, BusInfo.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		System.out.println(busInfo.getMsgBody().getItemList());
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		model.addAttribute("list", busInfo.getMsgBody().getItemList());
		
		return "bus/info-list";
	}
	
	@GetMapping("/common/stations-list/{busRouteId}")
	public String staionsList(Model model, @PathVariable String busRouteId) throws IOException {
		//http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?serviceKey=&busRouteId=100100152&resultType=json
		String apiURL ="http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute"
				+ "?serviceKey="+busKey
				+ "&resultType=json"
				+ "&busRouteId="+busRouteId;
		
		String response=OpenApi.get(apiURL);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>");
		//System.out.println(response);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>");
		
		ObjectMapper mapper = new ObjectMapper();
		StationByRoute rsponse = mapper.readValue(response, StationByRoute.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		System.out.println(rsponse.getMsgBody().getItemList());
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		model.addAttribute("list", rsponse.getMsgBody().getItemList());
		
		return "bus/stations-list";
	}
	
	
}
