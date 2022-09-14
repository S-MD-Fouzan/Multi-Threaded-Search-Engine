package com.amadeus.searchEngineV2.transaction;

import java.util.List;

public interface IUserTransactionManager {
	
	public List<Transaction> getAllTransactions();
	public List<Transaction> getTransactionsOfAUser(String username);
	public void addTransaction(Transaction transaction);

	
}
