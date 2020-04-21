package com.demo.upipayment.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="upi_account_transaction")
public class UPIAccountTransaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="upi_transactionid")
    private Long transactionId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="transaction_created")
    private Date transactionCreated;
	
	@Column(name="transaction_type")
    private String transactionType;
    
	@Column(name="transaction_status")
    private String transactionStatus;
    
	@Column(name="transaction_amount")
    private BigDecimal transactionAmount;
    
	@Column(name="available_balance")
    private BigDecimal availableBalance;
	
	@Column(name="upi_account_id")
	private Long upiAccountId;
    
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "upi_account_id",nullable = false)
//	private UPIAccount upiAccount;
	
//	public UPIAccount getUpiAccount() {
//		return upiAccount;
//	}
//
//	public void setUpiAccount(UPIAccount upiAccount) {
//		this.upiAccount = upiAccount;
//	}

	public UPIAccountTransaction() {
		
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionCreated() {
		return transactionCreated;
	}

	public void setTransactionCreated(Date transactionCreated) {
		this.transactionCreated = transactionCreated;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Long getUpiAccountId() {
		return upiAccountId;
	}

	public void setUpiAccountId(Long upiAccountId) {
		this.upiAccountId = upiAccountId;
	}

	
	
	

}
