package com.hersa.app.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ViewPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6223007001007850847L;
	
	private String companyName;
	
	public ViewPage() {
		super("");
		setCompanyName("2020 Hersa Corp.");
	}

	public boolean isDisplayAddBtn(){
		return !isAddReadingsPageInScope() && !isErrorPageInScope();
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
