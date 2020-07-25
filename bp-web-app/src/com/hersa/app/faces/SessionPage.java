package com.hersa.app.faces;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5589637102291747184L;
	
	private Date currentDate;
	private Long editBpId;
	
	private boolean isMobile;
	private boolean isInputTextPreferred;
	
	public SessionPage() {
		super("");
	}
	
	@PostConstruct
	public void onPageLoad() {
		currentDate = new Date();
		editBpId = null;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	public boolean isDisplayLoginUserInfo() {
		return getSessionUser() != null && getSessionUser().isAuthenticated();
	}

	public boolean isMobile() {
		return isMobile;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public Long getEditBpId() {
		return editBpId;
	}

	public void setEditBpId(Long editBpId) {
		this.editBpId = editBpId;
	}

	public boolean isEditRequest() {
		return isEditIdPopulated();
	}
	
	public boolean isEditIdPopulated() {
		return editBpId != null;
	}
	
	public boolean isInputTextPreferred() {
		return isInputTextPreferred;
	}

	public void setInputTextPreferred(boolean isInputTextPreferred) {
		this.isInputTextPreferred = isInputTextPreferred;
	}
}
