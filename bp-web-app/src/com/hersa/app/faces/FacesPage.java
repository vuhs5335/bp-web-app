package com.hersa.app.faces;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.hersa.app.bom.user.User;
import com.hersa.app.utils.SessionUtils;

public class FacesPage {

	private String title;
	private String pageHeader;
	private SessionUtils sessionUtils;
	
	public FacesPage(String title) {
		initialize();
		this.setTitle(title);
	}
	
	public FacesPage() {
		initialize();
	}
	
	public void onPageLoad() {
		//renderAppMessages();
	}
	
	private void renderAppMessages() {
		try {
			ApplicationPage app = getApplicationPage();
			app.displayMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		this.setTitle("");
		this.pageHeader  = "";
		this.sessionUtils = new SessionUtils();
	}
	
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public HttpServletRequest getHttpRequest() {
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		return request;
	}
	
	/*=== check view functions ===*/
	
	public boolean isAddReadingsPageInScope() {
		Object readingPage = getAddReadingPage();
		return readingPage != null;
	}
	
	public boolean isErrorPageInScope() {
		Object page = getViewMap().get("errorPage");
		return page != null;
	}
	
	public boolean isReadingsPageInScope() {
		Object page = getViewMap().get("readingsPage");
		return page != null;
	}
	
	public AddReadingPage getAddReadingPage() {
		return (AddReadingPage) getViewMap().get("addReadingPage");
	}
	
	public SessionPage getSessionPage() {
		SessionPage sessionPage = (SessionPage) sessionUtils.getSessionMap().get("sessionPage");
		return sessionPage;
	}
	
	public ApplicationPage getApplicationPage() {
		ApplicationPage page = (ApplicationPage) getFacesContext().getExternalContext().getApplicationMap().get("applicationPage");
		return page;
	}
	
	public Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}
	
	public void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
	}
	
	public void addWarningMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", message));
	}
	
	public void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}

	public void setSessionUser(User user) {
		sessionUtils.setSessionUser(user);
	}
	
	public User getSessionUser() {
		return sessionUtils.getSessionUser();
	}
	
}
