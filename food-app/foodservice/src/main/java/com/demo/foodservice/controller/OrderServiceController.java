package com.demo.foodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodservice.dto.OrderRequestDto;
import com.demo.foodservice.dto.OrderResponse;
import com.demo.foodservice.service.OrderService;

@RestController
@RequestMapping("/orderservice")
public class OrderServiceController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order/users/{userId}/{vendor}")
	public OrderResponse createOrder(@RequestBody OrderRequestDto orderRequestDto,
			@PathVariable String userId,
			@PathVariable String vendor) {

		return orderService.createOrder(orderRequestDto, userId, vendor);
	}
	
	@GetMapping("/order/{userId}")
	public OrderResponse getOrderHistory(@PathVariable String userId) {
		
		return orderService.getOrderHistory(userId);
	}

}
