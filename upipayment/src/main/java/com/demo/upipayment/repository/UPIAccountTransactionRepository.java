package com.demo.upipayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.upipayment.entity.UPIAccountTransaction;

public interface UPIAccountTransactionRepository extends PagingAndSortingRepository<UPIAccountTransaction, Long> {
	
	List<UPIAccountTransaction> findUPIAccountTransactionByUpiAccountId(Long upiAccountId);
	
	Page<UPIAccountTransaction> findByUpiAccountId(Long upiAccountId, Pageable pageable);

}
