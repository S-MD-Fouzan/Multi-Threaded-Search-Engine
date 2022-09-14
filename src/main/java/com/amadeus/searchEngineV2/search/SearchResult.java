package com.amadeus.searchEngineV2.search;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.amadeus.searchEngineV2.model.Path;

@Entity
public class SearchResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String filename;
	private String drive;
	private long times;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Path> pathsFound;
	
	public SearchResult() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}
	public String getDrive() {
		return drive;
	}
	public List<Path> getPathsFound() {
		return pathsFound;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setDrive(String drive) {
		this.drive = drive;
	}
	public void setPathsFound(List<Path> pathsFound) {
		this.pathsFound = pathsFound;
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}
	public void incrementTimes() {
		this.setTimes(getTimes()+1);
	}

	@Override
	public String toString() {
		return "SearchResult [id=" + id + ", filename=" + filename + ", drive=" + drive + ", times=" + times
				+ ", pathsFound=" + pathsFound + "]";
	}
	

}
