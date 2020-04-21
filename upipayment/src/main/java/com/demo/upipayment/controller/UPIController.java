package com.demo.upipayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.upipayment.dto.TransferAmountDTO;
import com.demo.upipayment.dto.UPIAccountDetail;
import com.demo.upipayment.entity.UPIAccountTransaction;
import com.demo.upipayment.service.UPIService;

@RestController
@RequestMapping("/upi")
public class UPIController {
	
	@Autowired
	private UPIService upiService;

	@PostMapping("/accounts/{phone}")
	public UPIAccountDetail registerUPIAccount(@PathVariable String phone) {
		return upiService.registerUPIAccount(phone);
		
	}
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transferAmount(@RequestBody TransferAmountDTO amountDTo,
			                      @PathVariable String fromPhone,
			                      @PathVariable String toPhone) {
		
		return upiService.transferAmount(amountDTo, fromPhone, toPhone);
				
	}
	
	@GetMapping("/accounts/{phone}")
	public List<UPIAccountTransaction> getTransactions(@PathVariable String phone){
		return upiService.getTransactions(phone);
	}
}
