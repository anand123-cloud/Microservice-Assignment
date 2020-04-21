package com.demo.foodservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.foodservice.entity.UserOrder;

public interface OrderRepository extends CrudRepository<UserOrder, String> {

}
