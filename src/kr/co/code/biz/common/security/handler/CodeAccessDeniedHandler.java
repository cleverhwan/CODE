/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.code.common.logging.CodeLogger;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


/**
* 업무명 : 인증 공통
* 설  명 : 접근권한이 없는 이용자 접근시 안내 페이지 이동 처리
*         intercept-url 에서 지정된 권한체크에서 권한 불충분일때 이 클래스가 호출됨
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Component(value="accessDeniedHandler")
public class CodeAccessDeniedHandler implements AccessDeniedHandler {

	private CodeLogger log = CodeLogger.getLogger("kr.co.skcc.mtc.biz.app.login");

	private String accessDeniedPage;

	public CodeAccessDeniedHandler() {
	}

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		RequestDispatcher rd = request.getRequestDispatcher(accessDeniedPage);
		rd.forward(request, response);
	}

	/**
	 * @return the accessDeniedPage
	 */
	public String getAccessDeniedPage() {
		return accessDeniedPage;
	}

	/**
	 * @param accessDeniedPage the accessDeniedPage to set
	 */
	public void setAccessDeniedPage(String accessDeniedPage) {
		this.accessDeniedPage = accessDeniedPage;
	}
}