package com.hersa.app.faces.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hersa.app.bom.apirequest.ApiRequest;
import com.hersa.app.bom.apirequest.ApiRequestManager;
import com.hersa.app.faces.FacesPage;

@ManagedBean
@ViewScoped
public class ApiConsolePage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4332236235340721617L;

	private List<ApiRequest> apiRequests;
	
	public ApiConsolePage() {
		super("API Console");
		setApiRequests(new ArrayList<ApiRequest>());
	}
	
	@PostConstruct
	public void onPageLoad() {
		loadRequestsList();
	}

	private void loadRequestsList() {
		try {
			ApiRequestManager arm = new ApiRequestManager();
			setApiRequests(arm.retrieveAllApiRequests());
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	public List<ApiRequest> getApiRequests() {
		return apiRequests;
	}

	public void setApiRequests(List<ApiRequest> apiRequests) {
		this.apiRequests = apiRequests;
	}
}
