package com.java.student_portal.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java.student_portal.entities.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Serializable> {

	List<Course> findByCodeIn(List<String> courseCodes);
	Course findOneByCode(String courseCode);
	
}
