package com.demo.upipayment.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.upipayment.entity.UPIAccount;

public interface UPIRepository extends CrudRepository<UPIAccount, Long>{

	
	UPIAccount findUPIAccountByPhone(String phone);

}
