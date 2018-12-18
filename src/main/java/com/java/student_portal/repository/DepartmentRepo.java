package com.java.student_portal.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.student_portal.entities.Department;


public interface DepartmentRepo extends JpaRepository<Department, Serializable>{

	Department findOneByCode(String departmentCode);
	
}
