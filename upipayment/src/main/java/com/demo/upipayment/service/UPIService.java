package com.demo.upipayment.service;

import java.util.List;

import com.demo.upipayment.dto.TransferAmountDTO;
import com.demo.upipayment.dto.UPIAccountDetail;
import com.demo.upipayment.entity.UPIAccountTransaction;

public interface UPIService {

	UPIAccountDetail registerUPIAccount(String phone);
	String transferAmount(TransferAmountDTO amountDTo,String fromPhone,String toPhone); 
	List<UPIAccountTransaction> getTransactions(String phone);

}
