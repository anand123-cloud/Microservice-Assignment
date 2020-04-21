package com.demo.bankingservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.PaymentRequest;
import com.demo.bankingservice.entity.Account;
import com.demo.bankingservice.entity.AccountTransaction;
import com.demo.bankingservice.entity.User;
import com.demo.bankingservice.repository.AccountRepository;
import com.demo.bankingservice.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	@Transactional
	public String makePayment(PaymentRequest paymentRequest, String userId) {
     
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			Account account = user.getAccounts().get(0);
			boolean isCardDetailVaid = validateCardDetails(account,paymentRequest);
			if(isCardDetailVaid) {
				
				account.setAccountBalance(account.getAccountBalance().subtract(paymentRequest.getAmount()));
				AccountTransaction debit=createTransactions(account,paymentRequest);
		       
			    account.getTransactions().add(debit);
				accountRepository.save(account);
				
			} else {
				throw new RuntimeException("card details not valid");
			}
		}
		else {
			throw new RuntimeException(userId+ "user not found");
		}
		return "success";
	}


	private AccountTransaction createTransactions(Account account, PaymentRequest paymentRequest) {
		AccountTransaction transaction=new AccountTransaction();
		transaction.setAccount(account);
		transaction.setAvailableBalance(account.getAccountBalance());
		transaction.setTransactionAmount(paymentRequest.getAmount());
		transaction.setTransactionCreated(new Date());
		transaction.setTransactionStatus("success");
		transaction.setTransactionType("debit");
		return transaction;
	}


	private boolean validateCardDetails(Account account, PaymentRequest paymentRequest) {
		boolean isCardValid=false;
		Account accountToCompare=new Account();
		accountToCompare.setCardNumber(paymentRequest.getCardNumber());	
		accountToCompare.setCvv(paymentRequest.getCvv());
		accountToCompare.setExpirationDate(paymentRequest.getExpirationDate());
		if(accountToCompare.equals(accountToCompare)) {
			isCardValid=true;
		}
		else {
			isCardValid=false;
		}
		return isCardValid;
	}


	@Override
	public List<AccountTransaction> findTransactions(String userId) {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		List<AccountTransaction> transactions=null;
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			Account account = user.getAccounts().get(0);
			transactions = account.getTransactions().subList(0, 5);
			
		} else {
			throw new RuntimeException("userId not found");
		}
		return transactions;
	}


	@Override
	public AccountDetail getAccountDetails(String userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		AccountDetail accountDetail=new AccountDetail();
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			Account account = user.getAccounts().get(0);
			accountDetail.setAccountBalance(account.getAccountBalance());
			accountDetail.setAccountNumber(account.getAccountNumber());
			accountDetail.setCardNumber(account.getCardNumber());
			accountDetail.setCvv(account.getCvv());
			accountDetail.setExpirationDate(account.getExpirationDate());
		} else {
			throw new RuntimeException("userId not found");
		}
		return accountDetail;
	}
	
	

}
