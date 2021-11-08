package com.lui.config;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class BatchErrorPages implements ErrorPageRegistrar{

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage[] errorPages = new ErrorPage[] {
			new ErrorPage(HttpStatus.FORBIDDEN, "/errorPage/403.html"),
			new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage/404.html"),
			new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage/500.html")
		};
		registry.addErrorPages(errorPages);
	}
	
}
