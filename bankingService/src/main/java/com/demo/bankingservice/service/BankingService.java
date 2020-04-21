package com.demo.bankingservice.service;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.TransferAmountDTO;

public interface BankingService {

	AccountDetail findAccountDetailByPhone(String phone);
	
	String transfer(String fromPhone, String toPhone,TransferAmountDTO transferAmountDTO);
}
