package com.hersa.app.faces;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import com.hersa.app.ApplicationException;

@ManagedBean
@ViewScoped
public class AdminConsolePage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 475672980383226720L;

	private String appMessage;
	
	private String appMessageTitle;
	
	public AdminConsolePage() {
		super("Admin Console");
	}
	
	public void onAddAppMessageBtnClick() {
		try {
			if (StringUtils.isEmpty(appMessage)) {
				throw new ApplicationException("Invlaid message.");
			}
			
			if (StringUtils.isEmpty(appMessageTitle)) {
				throw new ApplicationException("Invlaid message title.");
			}
			
			getApplicationPage().addAppMessage(new FacesMessage(appMessageTitle + ":", appMessage));
			
			appMessage = "";
			appMessageTitle = "";
			
		} catch(ApplicationException e) {
			addErrorMessage(e.getMessage());
		} catch (Exception e) {
			addErrorMessage("An error occured.");
		}
	}
	
	public String getAppMessage() {
		return appMessage;
	}

	public void setAppMessage(String appMessage) {
		this.appMessage = appMessage;
	}

	public String getAppMessageTitle() {
		return appMessageTitle;
	}

	public void setAppMessageTitle(String appMessageTitle) {
		this.appMessageTitle = appMessageTitle;
	}
	
}
