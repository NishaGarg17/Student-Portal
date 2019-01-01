package com.java.student_portal.common;

public enum ResponseMessage {
	
	SUCCESS(1000,"Success"),
	VALIDATION_ERROR(1001,"Validation Failed, Invalid Data"),
	STUDENT_NOT_FOUND(1002,"Unable to find student with given info"),
	TEACHER_NOT_FOUND(1003, "Unable to find teacher with given info"),
	INVALID_SUBJECT_DATA(1004, "Some Data is not correct given in subject Information");
	
	private String message;
	private int code;
	
	private ResponseMessage(int code, String message) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

}
