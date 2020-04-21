package com.demo.bankingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.PaymentRequest;
import com.demo.bankingservice.entity.AccountTransaction;
import com.demo.bankingservice.service.AccountService;

@RestController
@RequestMapping("/bankingservice")
public class BankingServiceController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/accounts/{userId}")
	public String makePayment(
			@RequestBody PaymentRequest paymentRequest,
			@PathVariable String userId){
		
		return accountService.makePayment(paymentRequest,userId);		
	}
	
	@GetMapping("/accounts/{userId}/transactions")
	public List<AccountTransaction> findTransactions(@PathVariable String userId) {
		return accountService.findTransactions(userId);
	}
	
	
	@GetMapping("/accounts/{userId}")
	public AccountDetail getAccountDetails(@PathVariable String userId) {
		return accountService.getAccountDetails(userId);
	}
}
