package com.amadeus.searchEngineV2.errorlog;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class ErrorLogService {
	
	
	Logger LOGGER = LogManager.getLogger(ErrorLogService.class);
	
	@Autowired
	private ErrorLogRepository logRepository;
	public List<ErrorLog> getLogs(){
		return logRepository.findAll();
	}
	public List<ErrorLog> getLogByStatus(ErrorStatus status){
		return logRepository.findLogByStatus(status);
	}
	public ErrorLog getLogById(long id) {
		return logRepository.findById(id).get();
	}
	
	public void log(String message) {
		LOGGER.error(message);
		this.logRepository.save(new ErrorLog(message,new Date(),ErrorStatus.UNRESOLVED));
		
	}
	

}
