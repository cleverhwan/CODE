/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.code.biz.common.security.util.CodeUserDetailsUtil;
import kr.co.code.common.logging.CodeLogger;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
* 업무명 : 인증 공통
* 설  명 : 로그인 성공 후 이동하는 페이지 처리
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Component(value="successHandler")
public class CodeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private CodeLogger log = CodeLogger.getLogger("kr.co.code.biz.app.login");
	
    public void onAuthenticationSuccess(HttpServletRequest request,
					HttpServletResponse response,
					Authentication auth)
		throws IOException, ServletException {
	
		// redirect
		
		log.debug("CodeAuthenticationSuccessHandler------------------------");
		response.sendRedirect(CodeUserDetailsUtil.getLoginSuccessForwardURL(request));		
	}
}