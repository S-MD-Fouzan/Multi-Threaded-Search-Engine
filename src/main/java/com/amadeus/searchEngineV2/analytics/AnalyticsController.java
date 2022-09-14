package com.amadeus.searchEngineV2.analytics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.searchEngineV2.model.AppUser;
import com.amadeus.searchEngineV2.search.SearchHistoryManager;
import com.amadeus.searchEngineV2.user.UserManager;

@RestController
@RequestMapping("/api")
public class AnalyticsController {
	
	
	@Autowired
	private AnalyticsManager manager;

	@GetMapping("/searches")
	public ResponseEntity<Long> getCount(){
		return ResponseEntity.ok().body(manager.getNumberOfSearchesMadeByUser());
	}
	@GetMapping("/getcount")
	public ResponseEntity<Long> getCount(@RequestParam String filename){
		return ResponseEntity.ok().body(manager.getTimesSearched(filename));
	}
	@GetMapping("/getmost")
	public ResponseEntity<String> getMost(){
		return ResponseEntity.ok().body(manager.getMostSearched());
	}
}
