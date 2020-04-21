package com.demo.upipayment.dto;

import java.math.BigDecimal;

public class AccountDetail {
	    private Long accountId;
		private String accountNumber;
	    private BigDecimal accountBalance;
	    private String phone;
	    private String firstName;
	    private String lastName;
	    
	    
		public AccountDetail() {
		}
		public Long getAccountId() {
			return accountId;
		}
		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    
	    
}
