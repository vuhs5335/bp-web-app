package com.hersa.app.faces;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ApplicationPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659628196302433038L;

	public ApplicationPage() {
		super("My Health App");
	}
}
