package com.java.student_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.student_portal.dto.CourseDto;
import com.java.student_portal.entities.Course;
import com.java.student_portal.repository.CourseRepo;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepo courseRepo;

	public String createCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setCode(courseDto.getCode());
		course.setDisplayName(courseDto.getDisplayName());
		course.setIsActive(courseDto.getIsActive());
		courseRepo.saveAndFlush(course);
		return "Course Created Successfully";
	}

}
