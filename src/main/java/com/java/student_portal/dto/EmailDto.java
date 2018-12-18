package com.java.student_portal.dto;

import java.io.Serializable;

public class EmailDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6728463840149362956L;
	
	private String email;
	private boolean isPrimary;
	private String emailTypeCode;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getEmailTypeCode() {
		return emailTypeCode;
	}
	public void setEmailTypeCode(String emailTypeCode) {
		this.emailTypeCode = emailTypeCode;
	}
}
