package com.amadeus.searchEngineV2.model;

public class RoleToUser {
	private String username;
	private String rolename;
	
	
	public RoleToUser() {
		super();
	}
	public RoleToUser(String username, String rolename) {
		super();
		this.username = username;
		this.rolename = rolename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	

}
