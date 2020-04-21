package com.demo.foodservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.foodservice.entity.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, String> {

}
