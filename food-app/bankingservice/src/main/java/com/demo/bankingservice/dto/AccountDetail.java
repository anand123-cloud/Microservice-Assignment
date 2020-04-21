package com.demo.bankingservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDetail {
		private String accountNumber;
	    private BigDecimal accountBalance;
	    private String cardNumber;
	    private Date expirationDate;
	    private String cvv;
	    
	    
		public String getAccountNumber() {
			return accountNumber;
		}


		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}


		public BigDecimal getAccountBalance() {
			return accountBalance;
		}


		public void setAccountBalance(BigDecimal accountBalance) {
			this.accountBalance = accountBalance;
		}


		public String getCardNumber() {
			return cardNumber;
		}


		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}


		public Date getExpirationDate() {
			return expirationDate;
		}


		public void setExpirationDate(Date expirationDate) {
			this.expirationDate = expirationDate;
		}


		public String getCvv() {
			return cvv;
		}


		public void setCvv(String cvv) {
			this.cvv = cvv;
		}


		public AccountDetail() {
		}
		
	    
}
