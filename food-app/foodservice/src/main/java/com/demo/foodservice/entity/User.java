package com.demo.foodservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @Column(name = "userid")
    private String userId;
	
	@Column(name = "firstname")
    private String firstName;
    
	@Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserOrder> userOrders;
    

	public User() {
    	
    }
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public List<UserOrder> getUserOrders() {
		return userOrders;
	}


	public void setUserOrders(List<UserOrder> userOrders) {
		this.userOrders = userOrders;
	}
}
