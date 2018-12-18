package com.java.student_portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.TeacherDto;
import com.java.student_portal.service.TeacherService;

@RestController
public class TeacherController {
	
		private TeacherService teacherService;
		
		@Autowired
		public TeacherController(TeacherService teacherService) {
			this.teacherService = teacherService;
		}
	
		@PostMapping(path = "/addTeacher")
		public ResponseEntity<String> addNewTeacher(@RequestBody List<TeacherDto> teacherDto) {
			ResponseMessage result = teacherService.addTeacher(teacherDto);
			return new ResponseEntity<String>(result.getMessage(), HttpStatus.CREATED);
			
		}
}
