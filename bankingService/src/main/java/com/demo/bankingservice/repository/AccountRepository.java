package com.demo.bankingservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.bankingservice.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
