package com.amadeus.searchEngineV2.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	private UserTransactionManager transactionManager;
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getTransactions(){
		return ResponseEntity.ok().body(this.transactionManager.getAllTransactions());
	}
	@GetMapping("/transactionsbyuser")
	public ResponseEntity<List<Transaction>> getTransactionsOfUser(@RequestParam String username){
		return ResponseEntity.ok().body(this.transactionManager.getTransactionsOfAUser(username));
	}
	

}
