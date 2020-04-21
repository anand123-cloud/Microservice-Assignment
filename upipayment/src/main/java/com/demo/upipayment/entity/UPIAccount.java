package com.demo.upipayment.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="upi_account")
public class UPIAccount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "upi_account_id", nullable = false, updatable = false)
    private Long upiAccountId;
	
	@Column(name = "firstname")
    private String firstName;
    
	@Column(name = "lastname")
    private String lastName;
    
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    
    @Column(name="account_balance")
    private BigDecimal accountBalance;
    
    @Column(name="bank_account_number")
    private String bankAccountNumber;
    
//    @OneToMany(mappedBy = "upiAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<UPIAccountTransaction> transactions;
    
    public UPIAccount() {
    	
    }

	public Long getUpiAccountId() {
		return upiAccountId;
	}

	public void setUpiAccountId(Long upiAccountId) {
		this.upiAccountId = upiAccountId;
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

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

//	public List<UPIAccountTransaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(List<UPIAccountTransaction> transactions) {
//		this.transactions = transactions;
//	}
//    
    

}
