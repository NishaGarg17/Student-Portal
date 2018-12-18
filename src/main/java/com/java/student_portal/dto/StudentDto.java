package com.java.student_portal.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


public class StudentDto implements Serializable{

	private static final long serialVersionUID = 3412417326664815142L;
	
	private String name;
	private String rollNo;
	private List<EmailDto> email;
	private List<PhoneDto> phone;
	private String dob;
    private List<AddressDto> address;
    private String departmentCode;
    private String courseCode;
    private String joiningDate;
    private int semester;
    
	
	
	public String getName() {
		return name;
	}
	@Required
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNo() {
		return rollNo;
	}
	@Required
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public List<AddressDto> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDto> address) {
		this.address = address;
	}
	public List<EmailDto> getEmail() {
		return email;
	}
	public void setEmail(List<EmailDto> email) {
		this.email = email;
	}
	public List<PhoneDto> getPhone() {
		return phone;
	}
	public void setPhone(List<PhoneDto> phone) {
		this.phone = phone;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	@Required
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getCourseCode() {
		return courseCode;
	}
	@Required
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	@Required
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getSemester() {
		return semester;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}


}
