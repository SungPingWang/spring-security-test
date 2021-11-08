package com.lui.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lui.bean.MyUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailService implements UserDetailsService {
	
	// 使用預設密碼登入系統時，應於登入後要求立即變更。（需增加這機制）
	// 密碼至少每六個月更換一次
	// 設定經過 15 分鐘閒置後，啟動設有密碼的螢幕保護程式。
	// 密碼變更時，至少不可以與前三次使用過之密碼相同

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		MyUser user = new MyUser();
		user.setUsername("admin");
		user.setPassword("123");
		log.info("[SOUT] >> 角色權限: " + user.getAuthorities().iterator().next());
		*/
		String[] roleAdminList = {"ADMIN"};
		String[] roleUserList = {"USER"};
		String[] roleAnomosList = {"NOAUTH"};
		
		if("admin".equals(username)) {
			UserDetails userDetails = User.builder()
					.username("admin")
					// .password("{noop}" + Password.getCorrectPassword()) // 設定這裡必須式的密碼
					.password("123")
					.roles(roleAdminList).build();
			return userDetails;
		}else if("user".equals(username)) {
			UserDetails userDetails = User.builder()
					.username("user")
					.password("123")
					.roles(roleUserList).build();
			return userDetails;
		}else {
			UserDetails userDetails = User.builder()
					.username(username)
					.password("123456")
					.roles(roleAnomosList).build();
			return userDetails;
		}
		
	}
	
}
