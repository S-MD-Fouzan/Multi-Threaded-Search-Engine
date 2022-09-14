package com.amadeus.searchEngineV2.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchResultRepository extends JpaRepository<SearchResult,Long> {

	List<SearchResult> findByFilename(String filename);
	void deleteByFilename(String filename);
	List<SearchResult> findByFilenameAndDrive(String filename,String drive);
}
