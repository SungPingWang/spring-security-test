package com.lui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lui.filter.ValidateCodeFilter;
import com.lui.handler.MyAuthenticationFailureHandler;
import com.lui.handler.MyAuthenticationSuccessHandler;


@SuppressWarnings("all")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
    private ValidateCodeFilter validateCodeFilter;

	@Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    }
	@Bean
	public UserDetailService userDetailService() {
		return new UserDetailService();
	}
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("123").roles("ADMIN")
			.and()
			.withUser("user").password("123").roles("USER");
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //添加驗證碼效驗過濾器
			.authorizeRequests()
			//.antMatchers(HttpMethod.GET, "/home/**").permitAll()
			//.antMatchers(HttpMethod.GET, "/auth/**").permitAll()
			.antMatchers("/code/image").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login").defaultSuccessUrl("/home/index")
				//  .successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
			.and()
	        .rememberMe()
	        .and()
			.csrf().disable();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/templates/**", "/static/**", "/css/**", "/js/**");
    }
	
}
