package com.hersa.app.security.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter({ "/SecurityFilter", "/private/*" })
public class SecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest  = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	
		SecurityRequest securityRequest = new SecurityRequest(httpRequest);
		
		int securityResponseCode = securityRequest.getResponseCode();
		
		switch (securityResponseCode) {
			case SecurityRequest.NORMAL :
				chain.doFilter(request, response);
				break;
			case SecurityRequest.UNAUTHORIZED :
				httpRequest.getRequestDispatcher("/redirect/error.xhtml").forward(securityRequest, httpResponse);
				break;
			case SecurityRequest.ERROR :
				httpRequest.getRequestDispatcher("/redirect/error.xhtml").forward(securityRequest, httpResponse);
				break;
			default:
				httpRequest.getRequestDispatcher("/redirect/error.xhtml").forward(securityRequest, httpResponse);
				break;
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
