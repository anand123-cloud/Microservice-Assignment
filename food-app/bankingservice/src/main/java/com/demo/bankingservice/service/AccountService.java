package com.demo.bankingservice.service;

import java.util.List;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.PaymentRequest;
import com.demo.bankingservice.entity.AccountTransaction;

public interface AccountService {
	
	String makePayment(PaymentRequest paymentRequest,String userId);
	List<AccountTransaction> findTransactions(String userId);
	AccountDetail getAccountDetails(String userId);
	

}
