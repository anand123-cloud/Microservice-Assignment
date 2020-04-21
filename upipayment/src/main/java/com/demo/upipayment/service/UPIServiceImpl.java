package com.demo.upipayment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.upipayment.dto.AccountDetail;
import com.demo.upipayment.dto.TransferAmountDTO;
import com.demo.upipayment.dto.UPIAccountDetail;
import com.demo.upipayment.entity.UPIAccount;
import com.demo.upipayment.entity.UPIAccountTransaction;
import com.demo.upipayment.feignclient.BankingServiceClient;
import com.demo.upipayment.repository.UPIAccountTransactionRepository;
import com.demo.upipayment.repository.UPIRepository;

@Service
public class UPIServiceImpl implements UPIService {
	
	@Autowired
	private UPIRepository upiRepository;
	@Autowired
	private UPIAccountTransactionRepository upiAccountTransactionRepository;
	
	@Autowired
	private BankingServiceClient bankingServiceClient;

	@Override
	public UPIAccountDetail registerUPIAccount(String phone) {
    //fetch account details from banking service
		ResponseEntity<AccountDetail> accountDetailByPhone = bankingServiceClient.getAccountDetailByPhone(phone);
		AccountDetail account=accountDetailByPhone.getBody();
		UPIAccount upiAccount=new UPIAccount();
		UPIAccountDetail upiAccountDetail=new UPIAccountDetail();
		if(account!=null) {
			upiAccount.setAccountBalance(account.getAccountBalance());
			upiAccount.setBankAccountNumber(account.getAccountNumber());
			upiAccount.setFirstName(account.getFirstName());
			upiAccount.setLastName(account.getLastName());
			upiAccount.setPhone(account.getPhone());
			UPIAccount savedUpiAccount = upiRepository.save(upiAccount);
			
			upiAccountDetail.setAccountBalance(savedUpiAccount.getAccountBalance());
			upiAccountDetail.setBankAccountNumber(savedUpiAccount.getBankAccountNumber());
			upiAccountDetail.setFirstName(savedUpiAccount.getFirstName());
			upiAccountDetail.setLastName(savedUpiAccount.getLastName());
			upiAccountDetail.setPhone(savedUpiAccount.getPhone());
			upiAccountDetail.setUpiAccountId(savedUpiAccount.getUpiAccountId());

		} else {
			throw new RuntimeException("account does not exist for this phone");
		}
		
		return upiAccountDetail;
	}

	@Override
	public String transferAmount(TransferAmountDTO amountDTo, String fromPhone, String toPhone) {
		String result = bankingServiceClient.transfer(amountDTo, fromPhone, toPhone);
		//success
		if(result!=null && result.equals("success")) {
			AccountDetail fromAccountDetail = bankingServiceClient.getAccountDetailByPhone(fromPhone).getBody();
			
			UPIAccountTransaction debitTransaction = createUPIAccountTransactionfromAccountDetail(fromAccountDetail,
					amountDTo,
					"debit");
			
			upiAccountTransactionRepository.save(debitTransaction);
			
			AccountDetail toAccountDetail = bankingServiceClient.getAccountDetailByPhone(toPhone).getBody();

			UPIAccountTransaction creditTransaction =createUPIAccountTransactionfromAccountDetail(toAccountDetail, amountDTo, "credit");
			upiAccountTransactionRepository.save(creditTransaction);
		}
		
		
		return "succcess";
	}

	
	private UPIAccountTransaction createUPIAccountTransactionfromAccountDetail(
			AccountDetail accountDetail,
			TransferAmountDTO amountDTo,
			String transactionType) {
		UPIAccountTransaction upiAccountTransaction=new UPIAccountTransaction();
		upiAccountTransaction.setAvailableBalance(accountDetail.getAccountBalance());
		upiAccountTransaction.setTransactionAmount(amountDTo.getAmount());
		upiAccountTransaction.setTransactionCreated(new Date());
		upiAccountTransaction.setTransactionStatus("success");
		upiAccountTransaction.setTransactionType(transactionType);
		upiAccountTransaction.setUpiAccountId(accountDetail.getAccountId());
		return upiAccountTransaction;
	}

	@Override
	public List<UPIAccountTransaction> getTransactions(String phone) {
		Long upiAccountId = upiRepository.findUPIAccountByPhone(phone).getUpiAccountId();
		
		Pageable paging = PageRequest.of(0, 5);

		return upiAccountTransactionRepository.findByUpiAccountId(upiAccountId, paging).getContent();
	    
	}


}
