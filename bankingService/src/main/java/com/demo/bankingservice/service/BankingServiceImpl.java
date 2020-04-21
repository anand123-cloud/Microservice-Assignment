package com.demo.bankingservice.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.TransferAmountDTO;
import com.demo.bankingservice.entity.Account;
import com.demo.bankingservice.entity.AccountTransaction;
import com.demo.bankingservice.entity.User;
import com.demo.bankingservice.repository.AccountRepository;
import com.demo.bankingservice.repository.UserRepository;


@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDetail findAccountDetailByPhone(String phone) {
     
		User user=userRepository.findUserByPhone(phone);
		AccountDetail accountDetail=new AccountDetail();
		if(user!=null) {
			List<Account> accounts = user.getAccounts();
		 if(accounts!=null && !accounts.isEmpty()) {
			 Account account=accounts.get(0);
			 accountDetail.setAccountBalance(account.getAccountBalance());
			 accountDetail.setAccountId(account.getAccountId());
			 accountDetail.setAccountNumber(account.getAccountNumber());
			 accountDetail.setFirstName(user.getFirstName());
			 accountDetail.setLastName(user.getLastName());
			 accountDetail.setPhone(user.getPhone());
			 
		 }
		} else {
			throw new RuntimeException("user not exist");
		}
		 return accountDetail;
		
	}

	@Override
	public String transfer(String fromPhone, String toPhone, TransferAmountDTO transferAmountDTO) {
		
		User fromUser=userRepository.findUserByPhone(fromPhone);
		User toUser=userRepository.findUserByPhone(toPhone);
		Account fromAccount = fromUser.getAccounts().get(0);
		Account toAccount = toUser.getAccounts().get(0);
		BigDecimal transferAmount=transferAmountDTO.getAmount();
		BigDecimal finalAmountFromAccount = fromAccount.getAccountBalance().subtract(transferAmount);
		
		fromAccount.setAccountBalance(finalAmountFromAccount);
		toAccount.setAccountBalance(toAccount.getAccountBalance().add(transferAmount));
		
		AccountTransaction fromAccountTransaction=new AccountTransaction();
		
		fromAccountTransaction.setAccount(fromAccount);
		fromAccountTransaction.setAvailableBalance(fromAccount.getAccountBalance());
		fromAccountTransaction.setTransactionAmount(transferAmount);
		fromAccountTransaction.setTransactionCreated(new Date());
		fromAccountTransaction.setTransactionStatus("success");
		fromAccountTransaction.setTransactionType("debit");
		fromAccount.getTransactions().add(fromAccountTransaction);
		accountRepository.save(fromAccount);
		
		
		
		AccountTransaction toAccountTransaction=new AccountTransaction();
		toAccountTransaction.setAccount(toAccount);
		toAccountTransaction.setAvailableBalance(toAccount.getAccountBalance());
		toAccountTransaction.setTransactionAmount(transferAmount);
		toAccountTransaction.setTransactionCreated(new Date());
		toAccountTransaction.setTransactionStatus("success");
		toAccountTransaction.setTransactionType("credit");
		toAccount.getTransactions().add(toAccountTransaction);
		accountRepository.save(toAccount);
		
		return "success";
	}

}
