package com.java.student_portal.dto;

import java.io.Serializable;
import java.util.List;


public class TeacherDto implements Serializable{
	private static final long serialVersionUID = -4628748264030245751L;

	private String name;
	private String dob;
	private String teacherCode;
	private List<EmailDto> emailList;
	private List<AddressDto> addressList;
	private List<PhoneDto> phoneList;
	private List<SubjectDto> subjectList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public List<EmailDto> getEmailList() {
		return emailList;
	}
	public void setEmailList(List<EmailDto> emailList) {
		this.emailList = emailList;
	}
	
	public List<AddressDto> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressDto> addressList) {
		this.addressList = addressList;
	}
	
	public List<PhoneDto> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<PhoneDto> phoneList) {
		this.phoneList = phoneList;
	}
	public List<SubjectDto> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SubjectDto> subjectList) {
		this.subjectList = subjectList;
	}
	public String getTeacherCode() {
		return teacherCode;
	}
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

}
