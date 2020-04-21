package com.demo.foodservice.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_order")
public class UserOrder {

	@Id
	@Column(name="orderid")
	private String orderId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="totalprice")
	private BigDecimal totalPrice;
	
	@Column(name="paymentid")
	private String paymentId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="userid")
	private User user;
	
	@OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
	private List<OrderItem> orderItems;
	
	@Column(name="order_date")
	private Date orderCreated;
	
	public UserOrder() {
		
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


}
