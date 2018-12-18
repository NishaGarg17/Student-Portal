package com.java.student_portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.AddressTypeDto;
import com.java.student_portal.dto.CourseDto;
import com.java.student_portal.dto.DepartmentDto;
import com.java.student_portal.dto.EmailTypeDto;
import com.java.student_portal.dto.PhoneTypeDto;
import com.java.student_portal.dto.StudentDto;
import com.java.student_portal.dto.SubjectDto;
import com.java.student_portal.service.AddressService;
import com.java.student_portal.service.CourseService;
import com.java.student_portal.service.DepartmentService;
import com.java.student_portal.service.EmailService;
import com.java.student_portal.service.PhoneService;
import com.java.student_portal.service.StudentService;
import com.java.student_portal.service.SubjectService;

@RestController
public class StudentController {
	
	private StudentService studentService;
	private AddressService addressService;
	private EmailService emailService;
	private PhoneService phoneService;
	private DepartmentService departmentService;
	private CourseService courseService;
	private SubjectService subjectService;
	
	@Autowired
	public StudentController(StudentService studentService, AddressService addressService, PhoneService phoneService, EmailService emailService, DepartmentService departmentService, CourseService courseService, SubjectService subjectService) {
		this.studentService = studentService;
		this.addressService = addressService;
		this.emailService = emailService;
		this.phoneService = phoneService;
		this.departmentService = departmentService;
		this.courseService = courseService;
		this.subjectService = subjectService;
	}
	
	@PostMapping(path="/addStudent")
	public ResponseEntity<String> addNewStudent(@RequestBody StudentDto student) {
		ResponseMessage result = studentService.createStudent(student);
		if(result.getCode() == 1000) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.CREATED);
		} else if(result.getCode() == 1001) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return null;
		
		
	}
	@PostMapping(path="/updateStudent")
	public ResponseEntity<String> updateStudent(@RequestBody StudentDto student) {
		ResponseMessage result = studentService.updateStudent(student);
		if(result.getCode() == 1002) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/addAddressType")
	public ResponseEntity<String> addNewAddressType(@RequestBody AddressTypeDto adTypeDto) {
	
		String result = addressService.createAddressType(adTypeDto);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PostMapping(path="/addEmailType")
	public ResponseEntity<String> addNewEmailType(@RequestBody EmailTypeDto emailTypeDto) {
	
		String result = emailService.createEmailType(emailTypeDto);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping(path="/addPhoneType")
	public ResponseEntity<String> addNewPhoneType(@RequestBody PhoneTypeDto phoneTypeDto) {
	
		String result = phoneService.createPhoneType(phoneTypeDto);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	@PostMapping(path="/addDepartment")
	public ResponseEntity<String> addNewDepartment(@RequestBody DepartmentDto departmentDto) {
		ResponseMessage result = departmentService.createDepartment(departmentDto);
		if(result.getCode() == 1000) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.CREATED);
		} else if(result.getCode() == 1001) {
			return new ResponseEntity<String>(result.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	@PostMapping(path="/addCourse")
	public ResponseEntity<String> addNewCourse(@RequestBody CourseDto courseDto) {
		String result = courseService.createCourse(courseDto);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/addSubject")
	public ResponseEntity<String> addNewSubject(@RequestBody List<SubjectDto> subjectDto) {
		String result = subjectService.createSubject(subjectDto);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@GetMapping(path="/getStudents")
	public ResponseEntity<List<StudentDto>> fetchAllStudents() {
		List<StudentDto> studentList = studentService.findAllStudents();
		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
	}
	
/*	@GetMapping(path="/getStudentsByZip")
	public ResponseEntity<List<StudentDto>> fetchAllStudentsByZipcode(@RequestParam("zipcode") String zipcode) {
		List<StudentDto> studentList = studentService.findAllStudentsByZipcode(zipcode);
		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
	}*/
}
