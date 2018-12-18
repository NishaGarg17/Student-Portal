package com.java.student_portal.dto;

import java.io.Serializable;

public class EmailTypeDto implements Serializable{
	private static final long serialVersionUID = 6387082740613053729L;
	private String code;
	private String displayName;
	private boolean isActive;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
