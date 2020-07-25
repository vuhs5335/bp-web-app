package com.hersa.app.security.filters;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.hersa.app.bom.user.User;
import com.hersa.app.bom.user.UserManager;
import com.hersa.app.utils.SessionKeys;

public class SecurityRequest extends HttpServletRequestWrapper{

	private User user;
	private int responseCode;
	private String message;
	private String callback;
	
	public static final int NORMAL = 000;
	public static final int UNAUTHORIZED = 100;
	public static final int ERROR  = 400;
	
	public SecurityRequest(HttpServletRequest request) {
		super(request);
		audit();
	}

	private void audit() {
		
		// Get referrer in case of error.
		
		String refererURL = this.getHeader("referer");
		
		callback = "";
		
		if (refererURL != null && !StringUtils.isEmpty(refererURL)) {
			String resource = StringUtils.substringAfterLast(refererURL, "/");
			callback = StringUtils.substringBeforeLast(resource, ".");
		}
		
		initialize();
		
		switch (responseCode) {
			case UNAUTHORIZED:
				message = "unauthorized request";
				break;
			case ERROR:
				message = "An error occurred.";
				break;
			default:
				message = "";
				break;
		}
	}

	private void initialize() {
		
		// Assume there is an error or condition not captured. 
		responseCode = ERROR;
		
		try {
			
			HttpSession session = this.getSession();

			UserManager userManager = new UserManager();
			
			this.user = (User) session.getAttribute(SessionKeys.USER);
			
			// Determine is user needs to be logged in.
			String requestURI = this.getRequestURI();
			
			boolean privateRequest = StringUtils.containsIgnoreCase(requestURI, "private");
			
			// if user is null, just create one.. we may need to store data here for 
			// public browsing. 
			if (user == null) {
				user = new User();
			}
			
			boolean sessionLogin = user.isSessionLogin();
			
			//reset login.
			user.setSessionLogin(false);
			
			// if not private request, allow.
			if (!privateRequest) {
				responseCode = NORMAL;
				return;
			}

			//  check for authentication
			if ((!user.isAuthenticated())) {
				responseCode = UNAUTHORIZED;
				return;
			}
			
			// check if admin function
			boolean adminRequest = StringUtils.containsIgnoreCase(requestURI, "admin");
			
			// If the request is not admin, then return normal response.
			// no need to validate admin privileges.
			if (!adminRequest) {
				responseCode = NORMAL;
				return;
			}
			
			Map<String, Integer> userRoleIndex = userManager.getRoleIndex();
			
			boolean isAdminUser = userRoleIndex.get(user.getRole()) == 0;
			
			// this is an admin request
			if (!isAdminUser) {
				responseCode = UNAUTHORIZED;
				return;
			}
			
			responseCode = NORMAL;
			
		} catch (Exception e) {
			responseCode = ERROR;
		}
		
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
