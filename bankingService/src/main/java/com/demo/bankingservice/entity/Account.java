package com.demo.bankingservice.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="saving_account")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="account_id")
    private Long accountId;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="account_balance")
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AccountTransaction> transactions;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userid",nullable = false)
    private User user;
    
    public Account() {
    	
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

	public List<AccountTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<AccountTransaction> transactions) {
		this.transactions = transactions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
    
    

}
