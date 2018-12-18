package com.java.student_portal.dto;

import java.io.Serializable;

public class CourseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9030262609717308409L;
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
