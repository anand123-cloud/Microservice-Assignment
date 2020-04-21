package com.demo.bankingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.TransferAmountDTO;
import com.demo.bankingservice.service.BankingService;

@RestController
@RequestMapping("/bankingservice")
public class BankingServiceController {
	
	@Autowired
	private BankingService bankingService;
	
	@GetMapping("/accounts/{phone}")
	public ResponseEntity<AccountDetail> getAccountDetailByPhone(@PathVariable String phone) throws RuntimeException {
		AccountDetail accountDetail=bankingService.findAccountDetailByPhone(phone);
		if(accountDetail!=null) {
			return new ResponseEntity<AccountDetail>(accountDetail, HttpStatus.OK);
		} else {
			throw new RuntimeException();
		}
	}
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transfer(@RequestBody TransferAmountDTO amountDTo,@PathVariable String fromPhone, @PathVariable String toPhone) {
		return bankingService.transfer(fromPhone, toPhone, amountDTo);
	}
	

}
