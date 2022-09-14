package com.amadeus.searchEngineV2.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.searchEngineV2.user.AppUserRepository;
import com.amadeus.searchEngineV2.user.UserManager;

@Service
public class UserTransactionManager implements IUserTransactionManager{

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserManager userManager;
	
	public List<Transaction> getAllTransactions(){
		return this.transactionRepository.findAll();
	}
	public List<Transaction> getTransactionsOfAUser(String username){
		return this.transactionRepository.getTransactionsByUser(this.userManager.getUser(username));
	}
	public void addTransaction(Transaction transaction) {
		transaction.setUser(this.userManager.getLoggedInUser());
		this.transactionRepository.save(transaction);
		
	}
	
}
