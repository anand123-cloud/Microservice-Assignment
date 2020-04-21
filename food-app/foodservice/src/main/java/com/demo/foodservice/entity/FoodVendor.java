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
@Table(name="restaurant")
public class FoodVendor {

	@Id
	@Column(name="restaurantid")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@OneToMany(mappedBy = "foodVendor",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JsonIgnore
	private List<MenuItem> menuItems;
	
	
	public FoodVendor() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	
	
	
}
