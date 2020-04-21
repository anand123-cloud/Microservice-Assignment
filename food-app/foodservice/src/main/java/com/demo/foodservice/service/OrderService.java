package com.demo.foodservice.service;

import com.demo.foodservice.dto.OrderRequestDto;
import com.demo.foodservice.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse createOrder(OrderRequestDto orderRequestDto, String userId, String vendor);

	OrderResponse getOrderHistory(String userId);

}
