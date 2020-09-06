package com.hersa.app.faces;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TestPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831728229379074845L;
	
	private Date date1;
	
	public TestPage() {
		super("Test Page");
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
}
