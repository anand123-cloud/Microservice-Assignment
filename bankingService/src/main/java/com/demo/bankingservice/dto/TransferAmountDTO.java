package com.demo.bankingservice.dto;

import java.math.BigDecimal;

public class TransferAmountDTO {
	
	private BigDecimal amount;
	
	public TransferAmountDTO() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransferAmountDTO(BigDecimal amount) {
		super();
		this.amount = amount;
	}
	
	

}
