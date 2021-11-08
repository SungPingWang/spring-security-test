package com.lui.entity;

public class RegisterRequest {
	
	private String username;
	private String password;
	private String passwordVaidate;
	private boolean checkRule;
	
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
	public String getPasswordVaidate() {
		return passwordVaidate;
	}
	public void setPasswordVaidate(String passwordVaidate) {
		this.passwordVaidate = passwordVaidate;
	}
	public boolean isCheckRule() {
		return checkRule;
	}
	public void setCheckRule(boolean checkRule) {
		this.checkRule = checkRule;
	}
	public RegisterRequest(String username, String password, String passwordVaidate, boolean checkRule) {
		super();
		this.username = username;
		this.password = password;
		this.passwordVaidate = passwordVaidate;
		this.checkRule = checkRule;
	}
	public RegisterRequest() {
		super();
	}
}
