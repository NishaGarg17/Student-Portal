package com.java.student_portal.dto;

import java.io.Serializable;
import java.util.List;


public class DepartmentDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2638855919725225227L;
	private String code;
	private String displayName;
	private Boolean isActive;
	private List<String> courseCodes;
	
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
	public List<String> getCourseCodes() {
		return courseCodes;
	}
	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}

}
