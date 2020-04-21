package com.demo.foodservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.foodservice.dto.AccountDetail;
import com.demo.foodservice.dto.PaymentRequest;


@FeignClient(name="banking-service",path = "/bankingservice")
public interface BankingServiceClient {
	
	@PostMapping("/accounts/{userId}")
	public String makePayment(@RequestBody PaymentRequest paymentRequest,@PathVariable String userId);
	@GetMapping("/accounts/{userId}")
	public AccountDetail getAccountDetails(@PathVariable String userId);
	
}
