package com.amadeus.searchEngineV2.errorlog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class ErrorLogController {
	
	@Autowired
	private ErrorLogService logService;
	
	@GetMapping("/logs")
	public ResponseEntity<List<ErrorLog>> getLogs() throws InterruptedException, ExecutionException{
		return ResponseEntity.ok().body(logService.getLogs());
	}
	@GetMapping("/log/{id}")
	public ResponseEntity<ErrorLog> getLogById(@PathVariable long id) throws InterruptedException, ExecutionException{
		return ResponseEntity.ok().body(logService.getLogById(id));
	}
	@GetMapping("/logbytype/**")
	public ResponseEntity<List<ErrorLog>> getLogsByType(@RequestParam String status) throws InterruptedException, ExecutionException{
		
		return ResponseEntity.ok().body(logService.getLogByStatus(ErrorStatus.valueOf(status)));
	}

}
