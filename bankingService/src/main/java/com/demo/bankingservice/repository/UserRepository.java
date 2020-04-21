package com.demo.bankingservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.bankingservice.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	User findUserByPhone(String phone);
}
