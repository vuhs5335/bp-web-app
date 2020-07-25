package com.hersa.app.bom.user;

public class User {

	private String userName;
	private String firstName;
	private String lastName;
	private boolean authenticated;
	private boolean sessionLogin;
	private String role;
	
	public User() {
		
	}
	
	public User(String userName, boolean authenticated, String role, boolean sessionLogin) {
		this.userName = userName;
		this.authenticated = authenticated;
		this.role = role;
		this.sessionLogin = sessionLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isSessionLogin() {
		return sessionLogin;
	}

	public void setSessionLogin(boolean sessionLogin) {
		this.sessionLogin = sessionLogin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
