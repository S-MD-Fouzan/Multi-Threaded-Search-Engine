package com.amadeus.searchEngineV2.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.searchEngineV2.model.AppUser;


public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	List<Transaction> getTransactionsByUser(AppUser user);
	
}
