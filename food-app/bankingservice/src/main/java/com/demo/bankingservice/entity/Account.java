package com.demo.bankingservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="saving_account")
public class Account {
	
	@Id
	@Column(name="account_number")
    private String accountNumber;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="account_balance")
    private BigDecimal accountBalance;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiry_date")
	private Date expirationDate;
	
	@Column(name="cvv")
	private String cvv;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AccountTransaction> transactions;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userid",nullable = false)
    private User user;
    
    public Account() {
    	
    }

    
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		return true;
	}




	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
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
