package com.amadeus.searchEngineV2.search;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.searchEngineV2.model.AppUser;
import com.amadeus.searchEngineV2.model.Role;
import com.amadeus.searchEngineV2.model.RoleToUser;
import com.amadeus.searchEngineV2.user.UserManager;

@RestController
@RequestMapping("/api")
public class SearchForm {
	
	@Autowired
	private SearchManager searchManager;
	
	@Autowired
	private SearchHistoryManager searchHistoryManager;
	
	@GetMapping("/search")
	public ResponseEntity<List<SearchResult>> getFileLocation(@RequestParam String filename) throws InterruptedException, ExecutionException{
		return ResponseEntity.ok().body(searchManager.getSearchResult(filename));
	}
	
	@GetMapping("/searchdrive")
	public ResponseEntity<List<SearchResult>> getFileLocation(@RequestParam String drive,@RequestParam String filename) throws InterruptedException, ExecutionException{
	
		return ResponseEntity.ok().body(searchManager.getSearchResult(drive, filename));
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<SearchResult>> getTotalHistory() throws InterruptedException, ExecutionException{
		return ResponseEntity.ok().body(searchHistoryManager.getHistory());
	}
	
	@GetMapping("/historybyname")
	public ResponseEntity<List<SearchResult>> getHistory(@RequestParam String filename) throws InterruptedException, ExecutionException{
		return ResponseEntity.ok().body(searchHistoryManager.getRecordsByFilename(filename));
	}
	
	@DeleteMapping("/history/{id}")
	public ResponseEntity<SearchResult> deleteById(@PathVariable long id) throws InterruptedException, ExecutionException{
		searchHistoryManager.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deletehistory")
	public ResponseEntity<SearchResult> deleteByName(@RequestParam String filename) throws InterruptedException, ExecutionException{
		searchHistoryManager.deleteByFilename(filename);
		return ResponseEntity.ok().build();
	}

	
}
