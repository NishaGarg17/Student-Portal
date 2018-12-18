package com.java.student_portal.dto;

import java.io.Serializable;

public class PhoneDto implements Serializable{

	private static final long serialVersionUID = 5924619545228938748L;
	
	private String phoneNo;
	private boolean isPrimary;
	private String phoneTypeCode;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public boolean getIsPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getPhoneTypeCode() {
		return phoneTypeCode;
	}
	public void setPhoneTypeCode(String phoneTypeCode) {
		this.phoneTypeCode = phoneTypeCode;
	}
}
