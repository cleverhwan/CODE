/*
 * Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.code.common.logging.CodeLogger;
import kr.co.code.common.message.CodePropertyHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
* 업무명 : 인증 공통
* 설  명 : 
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Component(value="loginUrlAuthenticationEntryPoint")
public class CodeLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private CodeLogger log = CodeLogger.getLogger("kr.co.skcc.mtc.biz.app.login");

	CodeLoginUrlAuthenticationEntryPoint()
    {
        super.setLoginFormUrl("/");
    }

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

	    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, CodePropertyHandler.getLoginInfoUrl());
	}
}