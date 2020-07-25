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

import org.apache.commons.lang.StringUtils;

import com.hersa.app.faces.SessionPage;

/**
 * Servlet Filter implementation class ApplicationFilter
 */
@WebFilter({ "/ApplicationFilter", "/*" })
public class ApplicationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ApplicationFilter() {
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
		
		HttpServletRequest httpRequest   = (HttpServletRequest) request;
	
		try {

			String uri = httpRequest.getRequestURI();
			
			// Determine if mobile. 
			String userAgent = httpRequest.getHeader("User-Agent");
			Boolean isMobile = StringUtils.containsIgnoreCase(userAgent, "mobi");
			
			SessionPage sessionPage = (SessionPage) httpRequest.getSession().getAttribute("sessionPage");
			
			boolean validSession = sessionPage != null;
			
			if (validSession) {
				sessionPage.setMobile(isMobile);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
