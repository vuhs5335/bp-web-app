package com.hersa.app.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ToolsPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 367249185553137472L;

	public ToolsPage() {
		super("Tools");
	}
}
