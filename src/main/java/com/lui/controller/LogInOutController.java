package com.lui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lui.entity.RegisterRequest;
import com.lui.service.register.RegisterVlidateServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
public class LogInOutController {
	
	@Autowired
	private RegisterVlidateServiceImpl registerValidateService; 

	@GetMapping("/error/{cause}")
    public String login_error(@PathVariable(name = "cause") String error, Model model) {
		
		model.addAttribute("errorMsg", "登入驗證錯誤，請重新進行登入");
		
        return "login";
    }
	
	@GetMapping("/errorMany")
    public String login_errorMany() {
		
        return "errorMany";
    }
	
	@GetMapping("/register")
    public String register_page() {
		log.info("********************");
		
        return "register.html";
    }
	
	@PostMapping("/register")
    public String register_post(RegisterRequest request, Model model) {
		log.info("checkRule: " + request.isCheckRule());
		if(!request.isCheckRule()) {
			model.addAttribute("errorMsg", "需同意條款");
			return "register.html";
		}
		
		int totalError = 0;
		
		if(!registerValidateService.isUserPass(request.getUsername())) {
			System.out.println("錯了1");
			totalError ++;
		}
		if (!registerValidateService.isPasswordPass(request.getPassword(), request.getPasswordVaidate())) {
			System.out.println("錯了2");
			totalError ++;
		}
		if(!registerValidateService.isUserPwdPass(request.getUsername(), request.getPassword())) {
			System.out.println("錯了3");
			totalError ++;
		}
		
		if(totalError > 0) {
			model.addAttribute("errorMsg", "驗證資格不符");
			return "register.html";
		}else {
			return "index.html";
		}
    }
	
}
