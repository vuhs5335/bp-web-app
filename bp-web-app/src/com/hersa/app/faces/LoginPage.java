package com.hersa.app.faces;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.hersa.app.bom.user.User;

@ManagedBean
@ViewScoped
public class LoginPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4162007323141884366L;
	
	private String userName;
	private String password;
	
	public LoginPage() {
		super("Log In");
		initialize();
	}

	private void initialize() {
		this.userName = "";
		this.password = "";
	}
	
	@PostConstruct
	public void onPageLoad() {
		
		// Reset the session.
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().invalidate();
	}

	public String onLoginBtnClick() {
		String response = "";
		
		try {
			if(userName!= null && (userName.equals("admin") || userName.equals("user")) && password != null && password.equals("pass")) {
				
				boolean sessionLogIn = true;
				
				User user = new User(userName, true, userName.toLowerCase(), sessionLogIn);
				
				setSessionUser(user);
				response = "dashboard";
			}else {
				throw new Exception("Invalid credentials");
			}
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
       
		return response;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
