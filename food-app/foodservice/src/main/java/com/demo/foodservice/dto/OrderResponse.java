package com.demo.foodservice.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderResponse {
	
	private Map<OrderDetail, List<OrderItemDto>> map=new HashMap<>();
	
	public OrderResponse() {
		
	}

	public Map<OrderDetail, List<OrderItemDto>> getMap() {
		return map;
	}

	public void setMap(Map<OrderDetail, List<OrderItemDto>> map) {
		this.map = map;
	}

	

}
