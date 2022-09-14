package com.amadeus.searchEngineV2.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeus.searchEngineV2.email.EmailService;
import com.amadeus.searchEngineV2.model.Path;
import com.amadeus.searchEngineV2.model.PathRepository;
import com.amadeus.searchEngineV2.transaction.Transaction;
import com.amadeus.searchEngineV2.transaction.UserTransactionManager;
import com.amadeus.searchEngineV2.user.UserManager;


@Service
public class SearchHistoryManager {
	
	@Autowired
	private SearchResultRepository searchResultRepository;
	
	@Autowired
	private PathRepository pathRepository;
	
	@Autowired
	private UserTransactionManager transactionManager;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserManager userManager;

	public List<SearchResult> getHistory(){
		this.transactionManager.addTransaction(new Transaction("Total History Retrieved"));
		return searchResultRepository.findAll();
	}
	@Transactional
	public List<SearchResult> getRecordsByFilename(String filename){

		userManager.getLoggedInUser().incrementSearchesMade(); 
		this.transactionManager.addTransaction(new Transaction("History of "+filename+" Retrieved"));
		List<SearchResult> results = searchResultRepository.findByFilename(filename);
		updateTimes(results);
		return results;
	}
	
	public void deleteByFilename(String filename) {
		
		this.transactionManager.addTransaction(new Transaction("History of "+filename+" deleted"));
		searchResultRepository.deleteByFilename(filename);
		this.emailService.sendSimpleEmail(userManager.getLoggedInUser().getUsername(), "History deleted", "Dear User, history with filename : "+filename+" deleted.");
		
	}

	public void addSearchResult(SearchResult result) {
		List<Path> paths = result.getPathsFound();
		paths.forEach((path)->{
			this.pathRepository.save(path);	
		});
		this.searchResultRepository.save(result); 
		
	}

	@Transactional
	public void incrementSearchesMade() {
		userManager.getLoggedInUser().incrementSearchesMade();
	}
	
	public void deleteById(long id) {
		this.transactionManager.addTransaction(new Transaction("History record with id : "+id+" deleted"));
		searchResultRepository.deleteById(id);
		this.emailService.sendSimpleEmail(userManager.getLoggedInUser().getUsername(), "History deleted", "Dear User, history with id : "+id+" deleted.");
		
	}
	
	@Transactional
	public void updateTimes(List<SearchResult> results) {
		for(SearchResult result:results) {
			result.incrementTimes();
		}
		
	}
	@Transactional
	public List<SearchResult> getRecordsByFilenameAndDrive(String filename,String drive) {
		userManager.getLoggedInUser().incrementSearchesMade(); 
		this.transactionManager.addTransaction(new Transaction("History of "+filename+" Retrieved"));
		List<SearchResult> results = searchResultRepository.findByFilenameAndDrive(filename,drive+":\\");
		updateTimes(results);
		return results;
	}

}
