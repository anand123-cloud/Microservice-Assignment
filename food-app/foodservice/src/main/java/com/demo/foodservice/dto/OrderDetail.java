package com.demo.foodservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail {

    private String orderId;
	
	private int quantity;
	
	private BigDecimal totalPrice;
	
	private String paymentId;

	private Date orderCreated;
	
	

	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", paymentId=" + paymentId + ", orderCreated=" + orderCreated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

	public OrderDetail() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}
	
	
}
