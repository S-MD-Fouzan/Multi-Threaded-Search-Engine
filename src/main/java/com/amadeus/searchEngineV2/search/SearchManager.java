package com.amadeus.searchEngineV2.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amadeus.searchEngineV2.driveFinder.DriveFinderFactory;
import com.amadeus.searchEngineV2.driveFinder.IDriveFinder;
import com.amadeus.searchEngineV2.driveFinder.InvalidDriveFinderChoiceException;
import com.amadeus.searchEngineV2.errorlog.ErrorLogService;
import com.amadeus.searchEngineV2.fileSearcher.FileSearchManager;


import org.springframework.scheduling.annotation.EnableAsync;

@Service
@EnableAsync
public class SearchManager{
	
	
	IDriveFinder driveFinder;
	
	@Autowired
	ErrorLogService logService;
	
	@Autowired
	private SearchHistoryManager searchHistoryManager;
	
	public SearchManager() {
		super();
		try {
			driveFinder = DriveFinderFactory.create("All");
		} catch (InvalidDriveFinderChoiceException e) {
			logService.log("Invalid drive choice");
		}
	}

	public List<SearchResult> getSearchResult(String filename) {
		List<SearchResult> results= this.searchHistoryManager.getRecordsByFilename(filename);
		if(results.size()>0) {
			return results;
		}
		System.out.println("Inside getSearchResult");
		return getPaths(driveFinder.getDrives(),filename);
	}
	
	public List<SearchResult> getSearchResult(String drive,String filename) {
		List<SearchResult> results= this.searchHistoryManager.getRecordsByFilenameAndDrive(filename,drive);
		if(results.size()>0) {
			
			return results;
			
		} 
		if(!driveFinder.getDrives().contains(drive+":\\")) {
			return results;
		}

		return getPaths(List.of(drive+":\\"),filename);
	}

	@Async
	public List<SearchResult> getPaths(List<String> drives,String filename) {
		

		List<SearchResult> result = new ArrayList<SearchResult>();
		
		if(drives.size()==0) {
			
			return result;
		}
		
		List<CompletableFuture<Void>> completableFutures = new ArrayList<CompletableFuture<Void>>();

		
		for(String drive:drives) {
			try {
			completableFutures.add(
					FileSearchManager.getFileLocations(new File(drive), filename).thenAccept(
							(pathsFound)->{
								if(pathsFound.size()!=0) {
									
								
								SearchResult resultForDrive = SearchResultFactory.getInstance();
								resultForDrive.setDrive(drive);
								resultForDrive.setFilename(filename);
								resultForDrive.setPathsFound(pathsFound);
								this.searchHistoryManager.addSearchResult(resultForDrive);
					
								result.add(resultForDrive);
								}
								
					})
			);
			
			}
			catch(Exception exception) {
				
				logService.log(exception.getMessage());
				
			}
		}
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
	
		this.searchHistoryManager.incrementSearchesMade();
		return result;
	}
	
}
