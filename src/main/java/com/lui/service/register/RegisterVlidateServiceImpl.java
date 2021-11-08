package com.lui.service.register;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterVlidateServiceImpl implements RegisterValidateServiceInteface{
	private final static String[] ALLOW_SYMBOL = {"!", "@", "#", "$", "%", "&", "*"};

	@Override
	public boolean isUserPass(String username) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isPasswordPass(String password, String passwordValid) {
		// password != passwordValid
		if(!password.equals(passwordValid)) {
			System.out.println("密碼驗證不符");
			return false;
		}
		// contains !@#$%&*
		boolean anyMatch = Arrays.stream(ALLOW_SYMBOL).anyMatch(p -> password.contains(p));
		if(!anyMatch) {
			System.out.println("要有特殊符號");
			return false;
		}
		// 必須要有大小寫
		boolean noUpper = Arrays.stream(password.split("")).noneMatch(p -> Character.isUpperCase(p.charAt(0)));
		boolean noLower = Arrays.stream(password.split("")).noneMatch(p -> Character.isLowerCase(p.charAt(0)));
		boolean noDigit = Arrays.stream(password.split("")).noneMatch(p -> Character.isDigit(p.charAt(0)));
		if(noUpper || noLower || noDigit) {
			System.out.println("要有大小寫");
			return false;
		}
		
		
		return true;
	}

	@Override
	public boolean isUserPwdPass(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
