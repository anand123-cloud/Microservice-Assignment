package com.demo.foodservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.foodservice.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
