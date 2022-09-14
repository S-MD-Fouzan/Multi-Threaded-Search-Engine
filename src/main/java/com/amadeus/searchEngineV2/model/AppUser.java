package com.amadeus.searchEngineV2.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AppUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String username;
	private String password;
	private long numOfsearches;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	public AppUser() {
		super();
	}
	public AppUser(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;	
		this.setNumOfsearches(0);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public long getNumOfsearches() {
		return numOfsearches;
	}
	public void setNumOfsearches(long numOfsearches) {
		this.numOfsearches = numOfsearches;
	}
	public void incrementSearchesMade() {
		this.setNumOfsearches(getNumOfsearches()+1);
	}
}
