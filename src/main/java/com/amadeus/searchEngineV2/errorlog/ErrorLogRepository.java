package com.amadeus.searchEngineV2.errorlog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ErrorLogRepository extends JpaRepository<ErrorLog,Long>{

	List<ErrorLog> findLogByStatus(ErrorStatus type);
}
