/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.access.AccessDeniedException;

/**
* 업무명 : 공통
* 설  명 :  app-config.xml에 정의된 exceptionResolver
*              각 Exception 유형에 따른 분기처리
*              Annotation 권한체크를 통한 권한 불충분일 경우 이 클래스가 호출됨
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class BaseExceptionResolver implements HandlerExceptionResolver {

	private String errorView = null;
	private String accessDeniedView = null;

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	public void setAccessDeniedView(String accessDeniedView) {
		this.accessDeniedView = accessDeniedView;
	}

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception) {

		//Hessian API 서비스시 HttpRequestMethodNotSupportedException이 발생하나 무시해도 됨
		//if( !(exception instanceof HttpRequestMethodNotSupportedException) ){
			exception.printStackTrace();
		//}

		String view = "";

		if(exception instanceof AccessDeniedException){
			view = this.accessDeniedView;

		}else{
			view = this.errorView;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		//mav.setViewName("redirect:" + view);
		mav.setViewName(view);
		//mav.setViewName(MtcUtil.getJsp(request) + view);

		return mav;
	}
}