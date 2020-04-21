package com.demo.upipayment.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.upipayment.dto.AccountDetail;
import com.demo.upipayment.dto.TransferAmountDTO;


@FeignClient(name="banking-service",path = "/bankingservice")
public interface BankingServiceClient {
	
	@GetMapping("/accounts/{phone}")
	public ResponseEntity<AccountDetail> getAccountDetailByPhone(@PathVariable String phone) throws RuntimeException;
	
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transfer(@RequestBody TransferAmountDTO amountDTo,@PathVariable String fromPhone, @PathVariable String toPhone);
}
