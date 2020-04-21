package com.demo.bankingservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bankingservice.dto.AccountDetail;
import com.demo.bankingservice.dto.UserRequestDto;
import com.demo.bankingservice.dto.UserResponseDto;
import com.demo.bankingservice.entity.Account;
import com.demo.bankingservice.entity.AccountTransaction;
import com.demo.bankingservice.entity.User;
import com.demo.bankingservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserResponseDto registerUser(UserRequestDto userDto) {
		
		User user=new User();
		user.setUserId(generateUniqueUserId());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhone(userDto.getPhone());
		Account account=creatAccount(user);
		user.setAccounts(Arrays.asList(account));
		User savedUser = userRepository.save(user);
		UserResponseDto userResponse = createUserResponse(savedUser);
		return userResponse;
	}

	private UserResponseDto createUserResponse(User user) {
		UserResponseDto responseDto=new UserResponseDto();
		responseDto.setAccountDetail(createAccountDetail(user.getAccounts().get(0)));
		responseDto.setEmail(user.getEmail());
		responseDto.setFirstName(user.getFirstName());
		responseDto.setLastName(user.getLastName());
		responseDto.setPhone(user.getPhone());
		responseDto.setUserId(user.getUserId());
		return responseDto;
		
	}

	private AccountDetail createAccountDetail(Account account) {
		AccountDetail accountDetail=new AccountDetail();
		accountDetail.setAccountBalance(account.getAccountBalance());
		accountDetail.setAccountNumber(account.getAccountNumber());
		accountDetail.setCardNumber(account.getCardNumber());
		accountDetail.setCvv(account.getCvv());
		accountDetail.setExpirationDate(account.getExpirationDate());
		return accountDetail;
	}

	private String generateUniqueUserId() {
		
		int unique = 100 + new Random().nextInt(900);	
		String userName="U0"+unique;
		return userName;
	}

	private Account creatAccount(User user) {
		Account account=new Account();
		account.setAccountBalance(new BigDecimal(10000.00));
		account.setAccountNumber(getUniqueAccountNumber());
		account.setCvv(getUniqueCVV());
		account.setCardNumber(getUniqueCardNumber());
		account.setExpirationDate(getExpiryDate());
		account.setUser(user);
		//AccountTransaction transaction=new AccountTransaction();
		//transaction.se
		AccountTransaction credit = createTransaction(account);
		account.setTransactions(Arrays.asList(credit));
		return account;
	}

	private AccountTransaction createTransaction(Account account) {
		AccountTransaction transaction=new AccountTransaction();

		transaction.setAccount(account);
		transaction.setAvailableBalance(new BigDecimal(10000.00));
		transaction.setTransactionAmount(new BigDecimal(10000.00));
		transaction.setTransactionCreated(new Date());
		transaction.setTransactionStatus("success");
		transaction.setTransactionType("credit");
		return transaction;
		
	}

	private Date getExpiryDate() {
		LocalDateTime today =  LocalDateTime.now();
		LocalDateTime expireDate = today.plusYears(4);
		return Date.from(expireDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	private String getUniqueCardNumber() {
		Random random = new Random();
		String cardNumber = String.format((Locale)null,
		                        "52%02d-%04d-%04d-%04d",
		                        random.nextInt(100),
		                        random.nextInt(10000),
		                        random.nextInt(10000),
		                        random.nextInt(10000));
		return cardNumber;
	}

	private String getUniqueCVV() {
		return Integer.toString(100 + new Random().nextInt(900));
	}

	private String getUniqueAccountNumber() {
		return UUID.randomUUID().toString();
	}
	

}
