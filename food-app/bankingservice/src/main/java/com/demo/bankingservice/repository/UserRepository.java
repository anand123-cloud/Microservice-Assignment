package com.demo.bankingservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.bankingservice.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
