package com.amadeus.searchEngineV2.errorlog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ErrorLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String message;
	private Date date;
	private ErrorStatus status;
	
	public ErrorLog() {
		super();
		
	}
	

	public ErrorLog(String message, Date date,ErrorStatus status) {
		super();
		this.message = message;
		this.date = date;
		this.status = status;
	}


	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public ErrorStatus getStatus() {
		return status;
	}


	public void setStatus(ErrorStatus status) {
		this.status = status;
	}
	
	

}
