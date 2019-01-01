package com.java.student_portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.student_portal.common.ResponseMessage;
import com.java.student_portal.dto.DepartmentDto;
import com.java.student_portal.entities.Course;
import com.java.student_portal.entities.Department;
import com.java.student_portal.repository.CourseRepo;
import com.java.student_portal.repository.DepartmentRepo;

import lombok.NonNull;

@Service
public class DepartmentService {
	private DepartmentRepo departmentRepo;
	private CourseRepo courseRepo;

	
	@Autowired
	public DepartmentService(@NonNull final DepartmentRepo departmentRepo,@NonNull final CourseRepo courseRepo) {
		this.departmentRepo = departmentRepo;
		this.courseRepo = courseRepo;
	}
	 public ResponseMessage createDepartment(DepartmentDto departmentDto) {
		Department department = new Department();
		department.setCode(departmentDto.getCode());
		department.setDisplayName(departmentDto.getDisplayName());
		department.setIsActive(departmentDto.getIsActive());
		List<String> courseCodes = departmentDto.getCourseCodes();
		System.out.println("courseCodes are: " + courseCodes.size());
		List<Course> courseList = courseRepo.findByCodeIn(courseCodes);
		System.out.println("courseList is: " + courseList.size() );
		if(courseList.size() != courseCodes.size()) {
			System.out.println("inside IFFFFFFF");
			return ResponseMessage.VALIDATION_ERROR;
		}
		department.setCourse(courseList);
		departmentRepo.saveAndFlush(department);
		return ResponseMessage.SUCCESS;
	}

}
