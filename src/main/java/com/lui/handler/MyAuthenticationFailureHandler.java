package com.lui.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lui.service.login.LoginTimeLimitService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LoginTimeLimitService loginTimeLimitService;
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /** @onAuthenticationFailure()
     *  在ValidateCodeFilter中凡是拋出錯誤 -- throw new ValidateCodeException()
     *  都會透過 authenticationFailureHandler.onAuthenticationFailure 在這裡進行實現
     *  將拋出的錯誤Message注入到這裏的 AuthenticationException e
     * */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("[SOUT] >> 登入錯誤");
        // 返回是否登入超過次數
        boolean isLimit = loginTimeLimitService.loginCountLimitProccess(new ServletWebRequest(httpServletRequest), sessionStrategy);
    	/*httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");*/
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("text/html;charset=utf-8");
        // 把先前 throw new ValidateCodeException("驗證碼 ERROR"); 錯誤丟入輸出	 
        if (isLimit) {
			httpServletResponse.getWriter().print("<p>驗證次數過多,你已被鎖定登入1分鐘，請稍後再試...</p>");
		}else if("TimeLimitError".equals(e.getMessage())) {
			httpServletResponse.getWriter().print("<p>你已被鎖定登入1分鐘，提醒您不斷嘗試刷新頁面將重新計算鎖定時間...</p>");
		}else {
			//httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				log.error("[ERRO] >> 睡眠出現錯誤");
			}
			httpServletResponse.sendRedirect("/security/auth/error/failed");
		}
    }
}
