package com.hersa.app.faces;

import java.io.Serializable;

public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8343989324415473084L;
	private String tagName;
	private boolean selected;
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
