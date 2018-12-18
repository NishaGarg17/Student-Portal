package com.java.student_portal.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.java.student_portal.entities.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher,Serializable>{
	Teacher findOneByTeacherCode(String teacherCode);
}
