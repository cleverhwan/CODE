package kr.co.code.biz.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.co.code.biz.common.wrapper.PreFixRequest;

/**
 * �겕濡쒖뒪 �뒪�겕由쏀똿 泥댄겕瑜� �쐞�빐�꽌 request瑜� 留듯븨 泥섎━�븯�뒗 �겢�옒�뒪
 * 
 */

public class XssFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new PreFixRequest((HttpServletRequest) request),
				response);
	}
}