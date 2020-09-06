package com.hersa.app.faces;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessagesPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114317009838609830L;

	private List<FacesMessage> messages;
	
	public MessagesPage() {
		super("Messages");
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}

	public List<FacesMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FacesMessage> messages) {
		this.messages = messages;
	}
}
