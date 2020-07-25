package com.hersa.app.bom.user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

	private Map<String, Integer> roleIndex;
	
	public UserManager() {
		roleIndex = new HashMap<String, Integer>();
		roleIndex.put("admin", 0);
		roleIndex.put("user", 1);
	}

	public Map<String, Integer> getRoleIndex() {
		return roleIndex;
	}

	public void setRoleIndex(Map<String, Integer> roleIndex) {
		this.roleIndex = roleIndex;
	}
	
}
