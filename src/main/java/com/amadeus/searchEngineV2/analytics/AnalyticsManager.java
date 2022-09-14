package com.amadeus.searchEngineV2.analytics;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.searchEngineV2.search.SearchHistoryManager;
import com.amadeus.searchEngineV2.search.SearchResult;
import com.amadeus.searchEngineV2.search.SearchResultRepository;
import com.amadeus.searchEngineV2.user.UserManager;

@Service
public class AnalyticsManager implements IAnalyticsManager {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SearchResultRepository searchResultRepository;
	
	@Autowired
	private EntityManager manager;
	

	@Override
	public long getNumberOfSearchesMadeByUser() {
		return userManager.getSearchesMade();
	}

	@Override
	public long getTimesSearched(String filename) {
		List<SearchResult> results = searchResultRepository.findByFilename(filename);
		if(results.size()==0) {
			return 0;
		}
		getMostSearched();
		return results.get(0).getTimes();
	}

	@Override
	public String getMostSearched() {
		Query query = manager.createQuery("SELECT U.filename from SearchResult U where U.times=(SELECT max(V.times) FROM SearchResult V)");
		return query.getResultList().get(0).toString();
	}
	

}
