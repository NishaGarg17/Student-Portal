package com.java.student_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.student_portal.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
	Student findStudentByRollNo(String rollNo);
}
