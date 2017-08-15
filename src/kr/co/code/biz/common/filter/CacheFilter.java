package kr.co.code.biz.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �럹�씠吏�瑜� �끂罹먯떆 �꽕�젙�븯�뒗 �븘�꽣 �겢�옒�뒪
 *
 */

public class CacheFilter implements Filter {

	
	public void destroy() {
	}


	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// if the ServletRequest is an instance of HttpServletRequest
		if (servletRequest instanceof HttpServletRequest) {
			// cast the object
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;

			if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Pragma", "no-cache");
			} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
				response.setHeader("Cache-Control", "no-cache");
			}
			response.setDateHeader("Expires", 0);
			
			// continue on in the filter chain with the FakeHeaderRequest and
			// ServletResponse objects
			filterChain.doFilter(request, response);
		} else {
			// otherwise, continue on in the chain with the ServletRequest and
			// ServletResponse objects
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}


	public void init(FilterConfig arg0) throws ServletException {
	}
}