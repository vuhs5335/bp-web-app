package com.hersa.app.faces;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.hersa.app.bom.user.User;
import com.hersa.app.security.filters.SecurityRequest;

@ManagedBean
@ViewScoped
public class ErrorPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6796574183318053840L;

	private int errorCode = -1;
	private String message;
	private String callback;
	private User user;
	
	public ErrorPage() {
		super("Error");
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		SecurityRequest request  = (SecurityRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		this.message   = request.getMessage();
		this.errorCode = request.getResponseCode();
		this.callback  = request.getCallback();
		this.setUser(getSessionUser());
		
	}

	public String goBack() {
	 return callback;	
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
