package com.amadeus.searchEngineV2.fileSearcher;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeus.searchEngineV2.model.Path;


@Service
@Transactional
public class FileSearchManager {


	@Async
	public static CompletableFuture<List<Path>> getFileLocations(File drive,String filename) {

		FileSearchThread thread = FileSearchThreadFactory.getInstance();
		thread.setDrive(drive);
		thread.setFileName(filename);
		return CompletableFuture.supplyAsync(thread);
		
	}
	
}

