package com.lui.service.register;

public interface RegisterValidateServiceInteface {

	boolean isUserPass(String username);
	boolean isPasswordPass(String password, String passwordValid);
	boolean isUserPwdPass(String username, String password);

	
}
