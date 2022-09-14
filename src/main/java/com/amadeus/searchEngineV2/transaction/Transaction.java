package com.amadeus.searchEngineV2.transaction;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.amadeus.searchEngineV2.model.AppUser;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;	
	private String operation;
	
	@ManyToOne
	private AppUser user;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
	public Transaction(String operation) {
		super();
		this.operation = operation;
	}
	public Transaction() {
		super();
	}


	public long getId() {
		return id;
	}

	public String getOperation() {
		return operation;
	}

	public AppUser getUser() {
		return user;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	

}
