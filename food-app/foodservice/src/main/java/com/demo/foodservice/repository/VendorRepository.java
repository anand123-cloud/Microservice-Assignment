package com.demo.foodservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.foodservice.entity.FoodVendor;

public interface VendorRepository extends CrudRepository<FoodVendor, String> {
	
	FoodVendor findByNameContainingIgnoreCase(String vendor);

}
