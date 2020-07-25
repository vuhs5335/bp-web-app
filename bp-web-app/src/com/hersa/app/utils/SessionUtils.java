package com.hersa.app.utils;

import java.util.Map;

import javax.faces.context.FacesContext;

import com.hersa.app.bom.user.User;

public class SessionUtils {

	public SessionUtils() {
		
	}
	
	public void setSessionUser(User user) {
		getSessionMap().put(SessionKeys.USER, user);
	}
	
	public User getSessionUser() {
		return (User) getSessionMap().get(SessionKeys.USER);
	}
	
	public Map<String, Object> getSessionMap(){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
}
