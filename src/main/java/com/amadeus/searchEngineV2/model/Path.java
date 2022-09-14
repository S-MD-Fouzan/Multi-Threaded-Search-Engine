package com.amadeus.searchEngineV2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Path {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String pathname;

	public Path(String pathname) {
		super();
		this.pathname = pathname;
	}
	public Path() {
		super();
	}
	public long getId() {
		return id;
	}
	public String getPathname() {
		return pathname;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	@Override
	public String toString() {
		return "Path [id=" + id + ", pathname=" + pathname + "]";
	}
	
	

}
