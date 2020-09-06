package com.hersa.app.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class ApplicationPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659628196302433038L;

	private static List<FacesMessage> messages;
	
	public ApplicationPage() {
		super("My Health App");
		messages = new ArrayList<FacesMessage>();
	}
	
	@PostConstruct
	public void onPageLoad() {
	
	}
	
	public void displayMessages() {
		FacesContext faces = FacesContext.getCurrentInstance();
		
		for (FacesMessage message : messages) {
			faces.addMessage("appMessagesForm:app-messages", message);
		}
	}
	
	public void addAppMessage(FacesMessage message) {
		messages.add(message);
	}
	
	public List<FacesMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FacesMessage> messages) {
		ApplicationPage.messages = messages;
	}
	
	public int getAppMessageCount() {
		return messages.size();
	}
	
}
