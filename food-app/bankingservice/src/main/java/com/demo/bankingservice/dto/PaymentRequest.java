package com.demo.bankingservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentRequest {

   
    private String cardNumber;
    private Date expirationDate;
    private String cvv; 
    private BigDecimal amount;
    
    public PaymentRequest() {
    	
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
			PaymentRequest other = (PaymentRequest) obj;
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
    
}
