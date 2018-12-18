package com.java.student_portal.dto;

import java.io.Serializable;

public class PhoneTypeDto implements Serializable{
	private static final long serialVersionUID = -7046919892942340042L;
	
	private String code;
	private String displayName;
	private Boolean isActive;
	
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
