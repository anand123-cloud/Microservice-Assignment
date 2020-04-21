package com.demo.bankingservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.bankingservice.entity.AccountTransaction;

public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransaction, Long> {
	

}
