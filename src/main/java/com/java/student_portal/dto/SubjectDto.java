package com.java.student_portal.dto;

import java.io.Serializable;

public class SubjectDto implements Serializable {

	private static final long serialVersionUID = 1155055697963533992L;
	private String subjectName;
	private String semester;
	private String courseCode;
	private String departmentCode;
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

}
