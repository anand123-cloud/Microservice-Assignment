package com.demo.bankingservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.bankingservice.entity.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

}
