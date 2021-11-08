package com.lui.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/management")
public class ManageController {

	@GetMapping("/getUsersIP")
	@Secured({"ROLE_ADMIN"})
	public String getUsersIP(HttpServletRequest request) {
		
		String remoteAddr = request.getRemoteAddr();
		log.info("[COUT] >> ip address: " + remoteAddr);
		log.info("[COUT] >> ip address: " + request.getRemoteHost());
		log.info("[COUT] >> ip address: " + request.getRemoteUser());
		
		return remoteAddr;
	}
	
}
