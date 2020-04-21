package com.demo.foodservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@Column(name="order_item_id")
	private String orderItemId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="item_name")
	private String itemName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="orderid")
	private UserOrder userOrder;
	
	public OrderItem() {
		
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}
	
	
	
	
	
	

}
