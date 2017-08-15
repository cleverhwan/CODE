/*
 * Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.handler;

import java.io.IOException;







//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.code.biz.common.security.cons.SecurityConstant;
import kr.co.code.biz.common.security.util.CodeUserDetailsUtil;
import kr.co.code.common.logging.CodeLogger;
import kr.co.code.common.message.CodePropertyHandler;
import kr.co.code.common.util.StringUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
* 업무명 : 인증 공통
* 설  명 : 로그아웃 성공 후 지정된 페이지로 이동
* 
* 일반 로그아웃 : /logout/logout.do
* 회원탈퇴 후 로그아웃 : /logout/logout.do?logoutType=WITHDRAW
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Component(value="logoutSuccessHandler")
public class CodeLogoutSuccessHandler implements LogoutSuccessHandler {

	private CodeLogger log = CodeLogger.getLogger("kr.co.skcc.mtc.biz.app.login");

 
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

		//RequestDispatcher rd = null;

        if (authentication != null) {
            // call sproc
        }
        
        //세션 언어정보 get
        Object locale = request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);

		//로그아웃 성공 후 세션에서 관리되는 정보 삭제--------------------------------------
        CodeUserDetailsUtil.removeSession(request);
		//----------------------------------------------------------------------------
        
        //세션 삭제 후 언어정보만 set
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);

    	String logoutType = StringUtil.defaultString(request.getParameter(SecurityConstant.LOGOUT_TYPE), "GENERAL");
    	String custId = StringUtil.defaultString(request.getParameter("custId"), "");
    	String returnUrl = StringUtil.defaultString(request.getParameter(SecurityConstant.RETURN_URL), "");

    	if(logoutType.equals("WITHDRAW")){
    		
			log.debug("회원 탈퇴로 인해 로그아웃 지정된 페이지로 이동-----------------------");
			response.sendRedirect(request.getContextPath() + CodePropertyHandler.getWithdrawLogoutResultUrl() + "?custId=" + java.net.URLEncoder.encode(custId, "UTF-8"));

		}else{
			
			//returnUrl 없는 경우
			if(returnUrl == ""){
				log.debug("일반 로그아웃 후 default 페이지로 이동-------------------");
				response.sendRedirect(request.getContextPath() + CodePropertyHandler.getLogoutResultUrl());

			//returnUrl 있는 경우
			}else{
				log.debug("일반 로그아웃 후 지정된 페이지로 이동-------------------");
				response.sendRedirect(request.getContextPath() + returnUrl);
			}
			
		}
    }
}

