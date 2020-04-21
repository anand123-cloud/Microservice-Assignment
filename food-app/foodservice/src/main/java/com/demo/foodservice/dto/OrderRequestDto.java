package com.demo.foodservice.dto;

import java.util.List;

public class OrderRequestDto {
	
	private List<OrderItemDto> orderItemRequests;
	
	public OrderRequestDto() {
		
	}

	public List<OrderItemDto> getOrderItemRequests() {
		return orderItemRequests;
	}

	public void setOrderItemRequests(List<OrderItemDto> orderItemRequests) {
		this.orderItemRequests = orderItemRequests;
	}

}
