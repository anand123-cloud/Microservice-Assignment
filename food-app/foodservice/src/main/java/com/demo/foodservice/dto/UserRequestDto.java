package com.demo.foodservice.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserRequestDto {
	
	@JsonIgnore
    private String userId;
	
	@NotBlank(message = "first name should not be blank")
    private String firstName;
	
    private String lastName;
    
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "phone should not be blank")
    private String phone;
    
    
    
    public UserRequestDto(String userId, String firstName, String lastName, String email, String phone) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	public UserRequestDto() {
    	
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
    
   
    
   
	
    
    



}
